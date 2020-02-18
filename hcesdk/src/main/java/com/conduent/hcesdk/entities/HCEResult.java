package com.conduent.hcesdk.entities;

public class HCEResult {
    private String tag;
    private String tagId;
    private String binaryValue;

    private HCEResult() {
    }

    public HCEResult(String tag, String tagId, String binaryValue) {
        this.tag = tag;
        this.tagId = tagId;
        this.binaryValue = binaryValue;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getBinaryValue() {
        return binaryValue;
    }

    public void setBinaryValue(String binaryValue) {
        this.binaryValue = binaryValue;
    }
}
