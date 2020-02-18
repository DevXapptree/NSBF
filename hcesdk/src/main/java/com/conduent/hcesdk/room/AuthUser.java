package com.conduent.hcesdk.room;

import android.arch.persistence.room.ColumnInfo;

public class AuthUser {

    @ColumnInfo(name = "holderDataCommercialId")
    private String holderDataCommercialId;

    public String getHolderDataCommercialId() {
        return holderDataCommercialId;
    }

    public void setHolderDataCommercialId(String holderDataCommercialId) {
        this.holderDataCommercialId = holderDataCommercialId;
    }
}
