package com.conduent.hcesdk.room.converters;

import android.arch.persistence.room.TypeConverter;
import com.conduent.hcesdk.room.ContractZone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ZoneConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<ContractZone> stringToList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<ContractZone>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(ArrayList<ContractZone> someObjects) {
        return gson.toJson(someObjects);
    }
}
