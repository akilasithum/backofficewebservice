package com.back.office.ws.entity;

public class PreOrder {

    private int preOrderId;
    private String PNR;
    private String customerName;
    private String serviceType;

    public int getPreOrderId() {
        return preOrderId;
    }

    public void setPreOrderId(int preOrderId) {
        this.preOrderId = preOrderId;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
