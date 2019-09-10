package com.back.office.ws.entity;

public class PaxDetails {

    private int paxId;
    private String orderId;
    private String paxName;
    private String pnr;
    private String seatNo;
    private String email;

    public int getPaxId() {
        return paxId;
    }

    public void setPaxId(int paxId) {
        this.paxId = paxId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaxName() {
        return paxName;
    }

    public void setPaxName(String paxName) {
        this.paxName = paxName;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
