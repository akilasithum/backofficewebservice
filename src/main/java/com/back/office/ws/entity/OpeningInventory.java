package com.back.office.ws.entity;

public class OpeningInventory {

    private int inventoryItemId;
    private String itemId;
    private int quantityInt;
    private String quantity;
    private int cartNoInt;
    private String cartNo;
    private String drawer;
    private String sifNo;

    public int getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(int inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getSifNo() {
        return sifNo;
    }

    public void setSifNo(String sifNo) {
        this.sifNo = sifNo;
    }

    public int getQuantityInt() {
        return quantityInt;
    }

    public void setQuantityInt(int quantityInt) {
        this.quantityInt = quantityInt;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getCartNoInt() {
        return cartNoInt;
    }

    public void setCartNoInt(int cartNoInt) {
        this.cartNoInt = cartNoInt;
    }

    public String getCartNo() {
        return cartNo;
    }

    public void setCartNo(String cartNo) {
        this.cartNo = cartNo;
    }
}
