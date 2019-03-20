package com.zensar.model;

import java.time.LocalDateTime;

public class Error {
    private Integer statusCode;
    private String message;
    private String documentation;
    private LocalDateTime timestamp;

    public Error(Integer statusCode, String message, String documentation){
        this.statusCode = statusCode;
        this.message = message;
        this.documentation = documentation;
        this.timestamp = LocalDateTime.now();
    }

    public Error() {
        this.timestamp = LocalDateTime.now();
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}