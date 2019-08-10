package com.back.office.ws.entity;

public class ItemSale {

    private int saleOrderId;
    private String orderId;
    private String itemId;
    private int itemIdInt;
    private String quantity;
    private int quantityInt;
    private String price;
    private float priceFloat;
    private float unitCostPrice;

    public int getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(int saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getItemIdInt() {
        return itemIdInt;
    }

    public void setItemIdInt(int itemIdInt) {
        this.itemIdInt = itemIdInt;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getQuantityInt() {
        return quantityInt;
    }

    public void setQuantityInt(int quantityInt) {
        this.quantityInt = quantityInt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getPriceFloat() {
        return priceFloat;
    }

    public void setPriceFloat(float priceFloat) {
        this.priceFloat = priceFloat;
    }

    public float getUnitCostPrice() {
        return unitCostPrice;
    }

    public void setUnitCostPrice(float unitCostPrice) {
        this.unitCostPrice = unitCostPrice;
    }
}
