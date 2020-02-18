package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;
import java.util.ArrayList;

public class HolderData implements Serializable {
    private ArrayList<Value> holderDataCardStatus;
    private ArrayList<Value> holderDataCommercialId;

    public HolderData() {
    }

    public ArrayList<Value> getHolderDataCardStatus() {
        return holderDataCardStatus;
    }

    public void setHolderDataCardStatus(ArrayList<Value> holderDataCardStatus) {
        this.holderDataCardStatus = holderDataCardStatus;
    }

    public ArrayList<Value> getHolderDataCommercialId() {
        return holderDataCommercialId;
    }

    public void setHolderDataCommercialId(ArrayList<Value> holderDataCommercialId) {
        this.holderDataCommercialId = holderDataCommercialId;
    }

}
