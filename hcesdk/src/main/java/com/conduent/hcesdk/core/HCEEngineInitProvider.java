package com.conduent.hcesdk.core;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class HCEEngineInitProvider extends ContentProvider {

    public HCEEngineInitProvider() {

    }

    @Override
    public boolean onCreate() {
        if (this.getContext() == null) {
            Log.i("HCEEngineInitProvider", "HCEEngine initialization unsuccessful");
        } else {
            HCEEngine.initializeApp(this.getContext());
            Log.i("HCEEngineInitProvider", "HCEEngine initialization successful");
        }
        return false;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @NonNull String[] strings, @NonNull String s, @NonNull String[] strings1, @NonNull String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @NonNull ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @NonNull String s, @NonNull String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @NonNull ContentValues contentValues, @NonNull String s, @NonNull String[] strings) {
        return 0;
    }
}
