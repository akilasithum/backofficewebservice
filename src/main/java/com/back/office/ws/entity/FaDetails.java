package com.back.office.ws.entity;

import java.util.Date;

public class FaDetails {

    private int faDetailsId;
    private String flightNo;
    private String sector;
    private Date flightDateFld;
    private String flightDate;
    private String faName;
    private int sifNoInt;
    private String sifNo;

    public int getFaDetailsId() {
        return faDetailsId;
    }

    public void setFaDetailsId(int faDetailsId) {
        this.faDetailsId = faDetailsId;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName;
    }

    public Date getFlightDateFld() {
        return flightDateFld;
    }

    public void setFlightDateFld(Date flightDateFld) {
        this.flightDateFld = flightDateFld;
    }

    public int getSifNoInt() {
        return sifNoInt;
    }

    public void setSifNoInt(int sifNoInt) {
        this.sifNoInt = sifNoInt;
    }

    public String getSifNo() {
        return sifNo;
    }

    public void setSifNo(String sifNo) {
        this.sifNo = sifNo;
    }
}
