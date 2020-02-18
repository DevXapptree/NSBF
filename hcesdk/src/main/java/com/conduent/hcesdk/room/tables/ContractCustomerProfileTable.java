package com.conduent.hcesdk.room.tables;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.conduent.hcesdk.room.DataValue;

@Entity(tableName = "contract_customer_profile_table")
public class ContractCustomerProfileTable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Embedded(prefix = "customer_profile_")
    private DataValue data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataValue getData() {
        return data;
    }

    public void setData(DataValue data) {
        this.data = data;
    }
}
