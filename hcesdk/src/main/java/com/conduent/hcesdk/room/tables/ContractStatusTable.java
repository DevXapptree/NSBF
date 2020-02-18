package com.conduent.hcesdk.room.tables;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.conduent.hcesdk.room.DataValue;

@Entity(tableName = "contract_status_table")
public class ContractStatusTable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Embedded(prefix = "contract_status_")
    private DataValue status;

    public void setId(int id) {
        this.id = id;
    }

    public DataValue getStatus() {
        return status;
    }

    public void setStatus(DataValue status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
}
