package com.back.office.ws.entity;

public class CreditCard {

    private int orderCreditCardId;
    private String orderId;
    private String creditCardNumber;
    private String cardHolderName;
    private String expireDate;
    private String amount;
    private float amountFloat;

    public int getOrderCreditCardId() {
        return orderCreditCardId;
    }

    public void setOrderCreditCardId(int orderCreditCardId) {
        this.orderCreditCardId = orderCreditCardId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public float getAmountFloat() {
        return amountFloat;
    }

    public void setAmountFloat(float amountFloat) {
        this.amountFloat = amountFloat;
    }
}
