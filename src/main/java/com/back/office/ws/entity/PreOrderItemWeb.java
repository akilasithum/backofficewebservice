package com.back.office.ws.entity;

public class PreOrderItemWeb {

    private int preOrderItemId;
    private int preOrderId;
    private String productNumber;
    private int qty;
    private String name;
    private String description;
    //private String category;

    public int getPreOrderItemId() {
        return preOrderItemId;
    }

    public void setPreOrderItemId(int preOrderItemId) {
        this.preOrderItemId = preOrderItemId;
    }

    public int getPreOrderId() {
        return preOrderId;
    }

    public void setPreOrderId(int preOrderId) {
        this.preOrderId = preOrderId;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }*/
}
