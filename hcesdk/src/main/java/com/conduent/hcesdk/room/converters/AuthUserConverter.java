package com.conduent.hcesdk.room.converters;

import android.arch.persistence.room.TypeConverter;
import com.conduent.hcesdk.room.AuthUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AuthUserConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<AuthUser> stringToList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<AuthUser>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(ArrayList<AuthUser> someObjects) {
        return gson.toJson(someObjects);
    }
}
