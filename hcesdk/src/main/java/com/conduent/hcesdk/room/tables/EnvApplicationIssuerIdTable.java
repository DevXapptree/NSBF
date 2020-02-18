package com.conduent.hcesdk.room.tables;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.conduent.hcesdk.room.DataValue;

@Entity(tableName = "env_application_issuer_id_table")
public class EnvApplicationIssuerIdTable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Embedded(prefix = "issuer_")
    private DataValue issuer;

    public DataValue getIssuer() {
        return issuer;
    }

    public void setIssuer(DataValue issuer) {
        this.issuer = issuer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
