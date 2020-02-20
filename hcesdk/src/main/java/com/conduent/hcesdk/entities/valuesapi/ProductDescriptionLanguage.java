package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductDescriptionLanguage implements Serializable {
    private String title;
    private String shortDesc;
    private String longDesc;
    private ArrayList<ProductDescriptionResource> resources;

    public ProductDescriptionLanguage() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public ArrayList<ProductDescriptionResource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<ProductDescriptionResource> resources) {
        this.resources = resources;
    }

}
