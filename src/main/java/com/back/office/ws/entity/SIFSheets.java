package com.back.office.ws.entity;

import java.util.List;

public class SIFSheets {

    private List<SIFSheet> sifEntries;

    public List<SIFSheet> getSifEntries() {
        return sifEntries;
    }

    public void setSifEntries(List<SIFSheet> sifEntries) {
        this.sifEntries = sifEntries;
    }
}
