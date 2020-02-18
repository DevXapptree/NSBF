package com.conduent.hcesdk;

import java.io.Serializable;

public class HCERecordData implements Serializable {

    private String record;

    public HCERecordData() {

    }

    public HCERecordData(String record) {
        this.record = record;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
