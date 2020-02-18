package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class TestRes implements Serializable {
    public String origin;

    public TestRes() {
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
