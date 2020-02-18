package com.conduent.hcesdk.room;

import android.arch.persistence.room.ColumnInfo;

public class ContractZone {
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "zoneLabel")
    private String zoneLabel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZoneLabel() {
        return zoneLabel;
    }

    public void setZoneLabel(String zoneLabel) {
        this.zoneLabel = zoneLabel;
    }
}
