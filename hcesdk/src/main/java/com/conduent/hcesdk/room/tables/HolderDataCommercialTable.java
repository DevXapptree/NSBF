package com.conduent.hcesdk.room.tables;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.conduent.hcesdk.room.DataValue;

@Entity(tableName = "holder_data_commercial_table")
public class HolderDataCommercialTable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Embedded(prefix = "commercial_")
    private DataValue commercial;

    public DataValue getCommercial() {
        return commercial;
    }

    public void setCommercial(DataValue commercial) {
        this.commercial = commercial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
