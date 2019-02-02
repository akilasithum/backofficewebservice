package com.back.office.ws.entity;

public class KitCode {

    private int kitCodeId;
    private String kitCode;
    private String description;
    private String serviceType;
    private String activateDate;
    private int noOfEquipments;
    private String packTypes;

    public int getKitCodeId() {
        return kitCodeId;
    }

    public void setKitCodeId(int kitCodeId) {
        this.kitCodeId = kitCodeId;
    }

    public String getKitCode() {
        return kitCode;
    }

    public void setKitCode(String kitCode) {
        this.kitCode = kitCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(String activateDate) {
        this.activateDate = activateDate;
    }

    public int getNoOfEquipments() {
        return noOfEquipments;
    }

    public void setNoOfEquipments(int noOfEquipments) {
        this.noOfEquipments = noOfEquipments;
    }

    public String getPackTypes() {
        return packTypes;
    }

    public void setPackTypes(String packTypes) {
        this.packTypes = packTypes;
    }
}
