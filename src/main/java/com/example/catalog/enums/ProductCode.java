package com.example.catalog.enums;

public enum ProductCode {
    SUCCESS(0, ResponseStatus.success.name(), "Request Processed Successfully successful"),
    FAILED_1(1, ResponseStatus.failed.name(), "No products found"),
    FAILED_2(1, ResponseStatus.failed.name(), "No product found"),
    FAILED_3(1, ResponseStatus.failed.name(), "Could Not Add Product");

    private final Integer code;
    private final String status;
    private String statusMsg;

    ProductCode(Integer code, String status, String statusMsg) {
        this.code=code;
        this.status=status;
        this.statusMsg=statusMsg;

    }

    public Integer getCode() {
        return code;
    }
    public String getStatusMsg() {
        return statusMsg;
    }
    public String getStatus() {
        return status;
    }
}
