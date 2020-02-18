package com.conduent.hcesdk.room.converters;

import android.arch.persistence.room.TypeConverter;
import com.conduent.hcesdk.room.DescriptionLangResource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DescriptionResourceConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<DescriptionLangResource> stringToList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<DescriptionLangResource>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(ArrayList<DescriptionLangResource> someObjects) {
        return gson.toJson(someObjects);
    }
}
