package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    private String productId;
    private ProductDescription description;
    private ArrayList<CommercialId> authUser;
    private ProductCustomData customData;

    public Product() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductDescription getDescription() {
        return description;
    }

    public void setDescription(ProductDescription description) {
        this.description = description;
    }

    public ArrayList<CommercialId> getAuthUser() {
        return authUser;
    }

    public void setAuthUser(ArrayList<CommercialId> authUser) {
        this.authUser = authUser;
    }

    public ProductCustomData getCustomData() {
        return customData;
    }

    public void setCustomData(ProductCustomData customData) {
        this.customData = customData;
    }
}
