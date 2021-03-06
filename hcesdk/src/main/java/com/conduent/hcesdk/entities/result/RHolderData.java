package com.conduent.hcesdk.entities.result;

import java.io.Serializable;

public class RHolderData implements Serializable {

    private String HolderDataCardStatus;
    private String HolderDataCommercialID;

    public RHolderData() {
    }

    public String getHolderDataCardStatus() {
        return HolderDataCardStatus;
    }

    public void setHolderDataCardStatus(String holderDataCardStatus) {
        HolderDataCardStatus = holderDataCardStatus;
    }

    public String getHolderDataCommercialID() {
        return HolderDataCommercialID;
    }

    public void setHolderDataCommercialID(String holderDataCommercialID) {
        HolderDataCommercialID = holderDataCommercialID;
    }

}
