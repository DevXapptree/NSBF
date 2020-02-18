package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class CustomData implements Serializable {
    public CustomData() {
    }

    private String customData1;
    private String customData2;
    private String customData3;
    private String customData4;

    public String getCustomData1() {
        return customData1;
    }

    public void setCustomData1(String customData1) {
        this.customData1 = customData1;
    }

    public String getCustomData2() {
        return customData2;
    }

    public void setCustomData2(String customData2) {
        this.customData2 = customData2;
    }
    public String getCustomData3() {
        return customData3;
    }

    public void setCustomData3(String customData3) {
        this.customData3 = customData3;
    }

    public String getCustomData4() {
        return customData4;
    }

    public void setCustomData4(String customData4) {
        this.customData4 = customData4;
    }

}
