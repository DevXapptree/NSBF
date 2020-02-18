package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class CommercialId implements Serializable {
    private String holderDataCommercialId;

    public CommercialId() {
    }

    public String getHolderDataCommercialId() {
        return holderDataCommercialId;
    }

    public void setHolderDataCommercialId(String holderDataCommercialId) {
        this.holderDataCommercialId = holderDataCommercialId;
    }
}
