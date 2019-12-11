package com.back.office.ws.entity;

import java.util.Date;

public class BondMessage {

    private int bondMessageId;
    private String flightNo;
    private String  messageBody;
    private Date flightDate;

    public int getBondMessageId() {
        return bondMessageId;
    }

    public void setBondMessageId(int bondMessageId) {
        this.bondMessageId = bondMessageId;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }
}
