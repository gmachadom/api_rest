package com.products.api_rest.models;

import org.springframework.stereotype.Component;

@Component
public class Message {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
