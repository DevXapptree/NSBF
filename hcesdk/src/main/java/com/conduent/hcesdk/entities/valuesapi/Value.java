package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class Value implements Serializable {
    private String id;
    private String value;

    public Value() {
    }

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
