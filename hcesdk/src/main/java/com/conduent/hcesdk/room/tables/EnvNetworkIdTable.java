package com.conduent.hcesdk.room.tables;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.conduent.hcesdk.room.DataValue;

@Entity(tableName = "env_network_id_table")
public class EnvNetworkIdTable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Embedded(prefix = "country_")
    private DataValue country;

    @Embedded(prefix = "network_")
    private DataValue network;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataValue getCountry() {
        return country;
    }

    public void setCountry(DataValue country) {
        this.country = country;
    }

    public DataValue getNetwork() {
        return network;
    }

    public void setNetwork(DataValue network) {
        this.network = network;
    }
}
