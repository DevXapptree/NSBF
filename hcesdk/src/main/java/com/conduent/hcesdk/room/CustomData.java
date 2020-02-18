package com.conduent.hcesdk.room;

import android.arch.persistence.room.ColumnInfo;

public class CustomData {

    @ColumnInfo(name = "customData3")
    private String customData3;
    @ColumnInfo(name = "customData4")
    private String customData4;

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
