package com.conduent.hcerepo.entities;

public class RecordFile {
    private int recLen;
    private String sfi;
    private int size;
    private RecordData recordData;

    public RecordFile(){}

    public int getRecLen() {
        return recLen;
    }

    public void setRecLen(int recLen) {
        this.recLen = recLen;
    }

    public String getSfi() {
        return sfi;
    }

    public void setSfi(String sfi) {
        this.sfi = sfi;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public RecordData getRecordData() {
        return recordData;
    }

    public void setRecordData(RecordData recordData) {
        this.recordData = recordData;
    }
}
