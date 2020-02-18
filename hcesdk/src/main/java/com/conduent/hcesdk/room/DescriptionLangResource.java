package com.conduent.hcesdk.room;

import android.arch.persistence.room.ColumnInfo;

public class DescriptionLangResource {
    @ColumnInfo(name = "value")
    private String value;
    @ColumnInfo(name = "tag")
    private String tag;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
