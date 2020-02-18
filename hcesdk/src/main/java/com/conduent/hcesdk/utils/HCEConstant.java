package com.conduent.hcesdk.utils;

import android.support.annotation.RestrictTo;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class HCEConstant {

    public static String BASE_URL = "https://mnsbf1.dynu.net/";
    public static int TIME_OUT = 60000;

    public enum HCEErrorCodes {
        DATA_NULL,
        PARSE_EXCEPTION
    }
}
