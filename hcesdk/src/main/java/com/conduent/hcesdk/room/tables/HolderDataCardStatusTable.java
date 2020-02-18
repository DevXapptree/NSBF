package com.conduent.hcesdk.room.tables;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.conduent.hcesdk.room.DataValue;

@Entity(tableName = "holder_data_card_status_table")
public class HolderDataCardStatusTable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Embedded(prefix = "card_status_")
    private DataValue cardStatus;

    public DataValue getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(DataValue cardStatus) {
        this.cardStatus = cardStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
