package com.back.office.ws.entity;

public class CartNumbers {

    private int cartNumberId;
    private String cartNumber;
    private String sifNo;

    public int getCartNumberId() {
        return cartNumberId;
    }

    public void setCartNumberId(int cartNumberId) {
        this.cartNumberId = cartNumberId;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    public String getSifNo() {
        return sifNo;
    }

    public void setSifNo(String sifNo) {
        this.sifNo = sifNo;
    }
}
