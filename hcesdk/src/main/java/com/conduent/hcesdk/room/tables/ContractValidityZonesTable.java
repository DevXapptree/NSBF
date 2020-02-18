package com.conduent.hcesdk.room.tables;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.conduent.hcesdk.room.ContractZone;

@Entity(tableName = "contract_validity_zones")
public class ContractValidityZonesTable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Embedded(prefix = "zone_")
    private ContractZone contractZone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContractZone getContractZone() {
        return contractZone;
    }

    public void setContractZone(ContractZone contractZone) {
        this.contractZone = contractZone;
    }
}
