package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class Zone implements Serializable {
    private String id;
    private String zoneLabel;

    public Zone() {
    }

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
