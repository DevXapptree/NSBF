package com.conduent.hcesdk;

import java.io.Serializable;
import java.util.ArrayList;

public class HCERecordFile implements Serializable {

    private String SFI;
    private ArrayList<HCERecordData> recordData;

    public HCERecordFile() {

    }

    public HCERecordFile(String SFI, ArrayList<HCERecordData> recordData) {
        this.SFI = SFI;
        this.recordData = recordData;
    }

    public String getSFI() {
        return SFI;
    }

    public void setSFI(String SFI) {
        this.SFI = SFI;
    }

    public ArrayList<HCERecordData> getRecordData() {
        return recordData;
    }

    public void setRecordData(ArrayList<HCERecordData> recordData) {
        this.recordData = recordData;
    }
}
