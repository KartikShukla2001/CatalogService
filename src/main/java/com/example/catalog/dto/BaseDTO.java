package com.example.catalog.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;

@NoArgsConstructor
public class BaseDTO {
    private Integer code;
    private String status;
    private String message;

    @JsonCreator
    public BaseDTO(@JsonProperty("code") Integer code,
                          @JsonProperty("status") String status,
                          @JsonProperty("message") String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public Object getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
