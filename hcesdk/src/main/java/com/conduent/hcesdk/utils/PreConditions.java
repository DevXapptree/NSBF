package com.conduent.hcesdk.utils;

import android.support.annotation.NonNull;

public class PreConditions {

    @NonNull
    public static <T> T checkNotNull(T var0, Object var1) {
        if (var0 == null) {
            throw new NullPointerException(String.valueOf(var1));
        } else {
            return var0;
        }
    }
}
