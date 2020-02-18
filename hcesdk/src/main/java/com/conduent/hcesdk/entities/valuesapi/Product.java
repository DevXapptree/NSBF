package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    private String productId;
    private Description description;
    private ArrayList<CommercialId> authUser;
    private CustomData customData;

    public Product() {
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

    public ArrayList<CommercialId> getAuthUser() {
        return authUser;
    }

    public void setAuthUser(ArrayList<CommercialId> authUser) {
        this.authUser = authUser;
    }

    public CustomData getCustomData() {
        return customData;
    }

    public void setCustomData(CustomData customData) {
        this.customData = customData;
    }
}
