package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class DescriptionResource implements Serializable {
    private String value;
    private String tag;

    public DescriptionResource() {
    }

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
