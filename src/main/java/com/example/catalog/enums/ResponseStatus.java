package com.example.catalog.enums;

public enum ResponseStatus {
    success,
    failed
    ;

    public static ResponseStatus getStatus(String status) {
        for (ResponseStatus responseStatus : ResponseStatus.values()) {
            if (responseStatus.name().equalsIgnoreCase(status)) {
                return responseStatus;
            }
        }
        return null;
    }
}
