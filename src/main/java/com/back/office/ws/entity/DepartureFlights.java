package com.back.office.ws.entity;

import java.util.Date;

public class DepartureFlights {

    private int depFlightId;
    private String flightNo;
    private Date flightTime;
    private String airline;
    private String to;
    private String destination;
    private String checkin;
    private String gate;
    private String status;

    public int getDepFlightId() {
        return depFlightId;
    }

    public void setDepFlightId(int depFlightId) {
        this.depFlightId = depFlightId;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Date getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Date flightTime) {
        this.flightTime = flightTime;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDestination() {
        return destination;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
