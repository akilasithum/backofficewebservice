package com.back.office.ws.entity;

import java.util.Date;

public class FaMessage {

    private Integer faMessageId;
    private String flightNumber;
    private Date flightDate;
    private String flightDateStr;
    private String faName;
    private String comment;

    public Integer getFaMessageId() {
        return faMessageId;
    }

    public void setFaMessageId(Integer faMessageId) {
        this.faMessageId = faMessageId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightDateStr() {
        return flightDateStr;
    }

    public void setFlightDateStr(String flightDateStr) {
        this.flightDateStr = flightDateStr;
    }

    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
