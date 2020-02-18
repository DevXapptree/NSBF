package com.conduent.hcesdk.room.tables;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;
import com.conduent.hcesdk.room.AuthUser;
import com.conduent.hcesdk.room.CustomData;
import com.conduent.hcesdk.room.Description;
import com.conduent.hcesdk.room.converters.AuthUserConverter;

import java.util.ArrayList;

@Entity(tableName = "product_table")
public class ProductTable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "productId")
    private String productId;

    @Embedded(prefix = "product_")
    private Description description;

    @ColumnInfo(name = "authUser")
    @TypeConverters(AuthUserConverter.class)
    private ArrayList<AuthUser> authUser;

    @Embedded(prefix = "custom_")
    private CustomData customData;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public ArrayList<AuthUser> getAuthUser() {
        return authUser;
    }

    public void setAuthUser(ArrayList<AuthUser> authUser) {
        this.authUser = authUser;
    }

    public CustomData getCustomData() {
        return customData;
    }

    public void setCustomData(CustomData customData) {
        this.customData = customData;
    }
}
