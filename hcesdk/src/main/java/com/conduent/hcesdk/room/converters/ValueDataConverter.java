package com.conduent.hcesdk.room.converters;

import android.arch.persistence.room.TypeConverter;
import com.conduent.hcesdk.room.DataValue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ValueDataConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<DataValue> stringToList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<DataValue>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(ArrayList<DataValue> someObjects) {
        return gson.toJson(someObjects);
    }
}
