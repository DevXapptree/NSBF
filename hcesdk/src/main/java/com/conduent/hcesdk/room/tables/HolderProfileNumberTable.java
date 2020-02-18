package com.conduent.hcesdk.room.tables;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.conduent.hcesdk.room.DataValue;

@Entity(tableName = "holder_profile_number_table")
public class HolderProfileNumberTable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Embedded(prefix = "profile_")
    private DataValue profile;

    public DataValue getProfile() {
        return profile;
    }

    public void setProfile(DataValue profile) {
        this.profile = profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
