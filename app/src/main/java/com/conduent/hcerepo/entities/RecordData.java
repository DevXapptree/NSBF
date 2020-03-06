package com.conduent.hcerepo.entities;

import java.util.ArrayList;

public class RecordData {

    public RecordData() {
    }

    public ArrayList<String> getRecord() {
        return record;
    }

    public void setRecord(ArrayList<String> record) {
        this.record = record;
    }

    private ArrayList<String> record;
}
