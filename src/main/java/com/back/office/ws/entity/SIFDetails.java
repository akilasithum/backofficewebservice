package com.back.office.ws.entity;

import java.util.Date;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="sifDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class SIFDetails {

    private String sifNo;
    private int sifNoInt;
    private String deviceId;
    private Date downloaded;
    private String packedFor;
    private String packedTime;
    private Date packedTimeDate;
    private String crewOpenedTime;
    private Date crewOpenedTimeDate;
    private String crewClosedTime;
    private Date crewClosedTimeDate;

    public String getSifNo() {
        return sifNo;
    }

    public void setSifNo(String sifNo) {
        this.sifNo = sifNo;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(Date downloaded) {
        this.downloaded = downloaded;
    }

    public String getPackedFor() {
        return packedFor;
    }

    public void setPackedFor(String packedFor) {
        this.packedFor = packedFor;
    }

    public int getSifNoInt() {
        return sifNoInt;
    }

    public void setSifNoInt(int sifNoInt) {
        this.sifNoInt = sifNoInt;
    }

    public String getPackedTime() {
        return packedTime;
    }

    public void setPackedTime(String packedTime) {
        this.packedTime = packedTime;
    }

    public Date getPackedTimeDate() {
        return packedTimeDate;
    }

    public void setPackedTimeDate(Date packedTimeDate) {
        this.packedTimeDate = packedTimeDate;
    }

    public String getCrewOpenedTime() {
        return crewOpenedTime;
    }

    public void setCrewOpenedTime(String crewOpenedTime) {
        this.crewOpenedTime = crewOpenedTime;
    }

    public Date getCrewOpenedTimeDate() {
        return crewOpenedTimeDate;
    }

    public void setCrewOpenedTimeDate(Date crewOpenedTimeDate) {
        this.crewOpenedTimeDate = crewOpenedTimeDate;
    }

    public String getCrewClosedTime() {
        return crewClosedTime;
    }

    public void setCrewClosedTime(String crewClosedTime) {
        this.crewClosedTime = crewClosedTime;
    }

    public Date getCrewClosedTimeDate() {
        return crewClosedTimeDate;
    }

    public void setCrewClosedTimeDate(Date crewClosedTimeDate) {
        this.crewClosedTimeDate = crewClosedTimeDate;
    }
}
