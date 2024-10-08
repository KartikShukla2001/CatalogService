package com.example.catalog.config;

import com.example.catalog.controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestTimingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestTimingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // Check if the request came from cache
        boolean fromCache = response.getHeader("X-Cache") != null && response.getHeader("X-Cache").equals("HIT");

        if (fromCache) {
            logger.info("Request for {} completed in {} ms (FROM CACHE)", request.getRequestURI(), executionTime);
        } else {
            logger.info("Request for {} completed in {} ms (WITHOUT CACHE)", request.getRequestURI(), executionTime);
        }
    }
}
