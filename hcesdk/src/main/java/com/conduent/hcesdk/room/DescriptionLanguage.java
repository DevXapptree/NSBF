package com.conduent.hcesdk.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.TypeConverters;
import com.conduent.hcesdk.room.converters.DescriptionResourceConverter;

import java.util.ArrayList;

public class DescriptionLanguage{
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "shortDesc")
    private String shortDesc;

    @ColumnInfo(name = "longDesc")
    private String longDesc;

    @ColumnInfo(name = "resources")
    @TypeConverters(DescriptionResourceConverter.class)
    private ArrayList<DescriptionLangResource> resources;

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

    public ArrayList<DescriptionLangResource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<DescriptionLangResource> resources) {
        this.resources = resources;
    }

}
