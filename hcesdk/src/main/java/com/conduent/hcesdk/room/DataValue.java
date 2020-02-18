package com.conduent.hcesdk.room;

import android.arch.persistence.room.ColumnInfo;

public class DataValue {
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "value")
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
