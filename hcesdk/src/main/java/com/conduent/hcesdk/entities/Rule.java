package com.conduent.hcesdk.entities;

public class Rule {
    private String dataId;
    private String dataType;
    private String bitmapUsed;
    private String bitmapId;
    private String bitmapPos;
    private String dataLength;

    private Rule() {

    }

    public Rule(String dataId, String dataType, String bitmapUsed, String bitmapId, String bitmapPos, String dataLength) {
        this.dataId = dataId;
        this.dataType = dataType;
        this.bitmapUsed = bitmapUsed;
        this.bitmapId = bitmapUsed;
        this.bitmapPos = bitmapPos;
        this.dataLength = dataLength;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getBitmapUsed() {
        return bitmapUsed;
    }

    public void setBitmapUsed(String bitmapUsed) {
        this.bitmapUsed = bitmapUsed;
    }

    public String getBitmapId() {
        return bitmapId;
    }

    public void setBitmapId(String bitmapId) {
        this.bitmapId = bitmapId;
    }

    public String getBitmapPos() {
        return bitmapPos;
    }

    public void setBitmapPos(String bitmapPos) {
        this.bitmapPos = bitmapPos;
    }

    public String getDataLength() {
        return dataLength;
    }

    public void setDataLength(String dataLength) {
        this.dataLength = dataLength;
    }

}
