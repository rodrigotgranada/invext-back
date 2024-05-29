package com.invext.distribuidor.model;

import java.util.UUID;

public class SupportRequest {
    private String id;
    private String type;
    private String customerName;
    private String customerMessage;

    public SupportRequest() {
        this.id = UUID.randomUUID().toString();
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
