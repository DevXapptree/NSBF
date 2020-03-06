package com.conduent.hcesdk.entities.result;

import java.io.Serializable;

public class RHolder implements Serializable {
    private RHolderData HolderData;

    public RHolder() {
    }

    public RHolderData getHolderData() {
        return HolderData;
    }

    public void setHolderData(RHolderData holderData) {
        HolderData = holderData;
    }
}
