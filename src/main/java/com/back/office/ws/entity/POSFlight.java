package com.back.office.ws.entity;

import java.util.Date;

public class POSFlight {

    private int flightUniqueId;
    private String flightId;
    private String flightName;
    private String flightDate;
    private Date flightDateFld;
    private String flightFrom;
    private String flightTo;
    private String eClassPaxCount;
    private int eClassPaxCountInt;
    private String bClassPaxCount;
    private int bClassPaxCountInt;
    private String sifNo;
    private int sifNoInt;

    public int getFlightUniqueId() {
        return flightUniqueId;
    }

    public void setFlightUniqueId(int flightUniqueId) {
        this.flightUniqueId = flightUniqueId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public Date getFlightDateFld() {
        return flightDateFld;
    }

    public void setFlightDateFld(Date flightDateFld) {
        this.flightDateFld = flightDateFld;
    }

    public String getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(String flightFrom) {
        this.flightFrom = flightFrom;
    }

    public String getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }

    public String geteClassPaxCount() {
        return eClassPaxCount;
    }

    public void seteClassPaxCount(String eClassPaxCount) {
        this.eClassPaxCount = eClassPaxCount;
    }

    public int geteClassPaxCountInt() {
        return eClassPaxCountInt;
    }

    public void seteClassPaxCountInt(int eClassPaxCountInt) {
        this.eClassPaxCountInt = eClassPaxCountInt;
    }

    public String getbClassPaxCount() {
        return bClassPaxCount;
    }

    public void setbClassPaxCount(String bClassPaxCount) {
        this.bClassPaxCount = bClassPaxCount;
    }

    public int getbClassPaxCountInt() {
        return bClassPaxCountInt;
    }

    public void setbClassPaxCountInt(int bClassPaxCountInt) {
        this.bClassPaxCountInt = bClassPaxCountInt;
    }

    public String getSifNo() {
        return sifNo;
    }

    public void setSifNo(String sifNo) {
        this.sifNo = sifNo;
    }

    public int getSifNoInt() {
        return sifNoInt;
    }

    public void setSifNoInt(int sifNoInt) {
        this.sifNoInt = sifNoInt;
    }
}
