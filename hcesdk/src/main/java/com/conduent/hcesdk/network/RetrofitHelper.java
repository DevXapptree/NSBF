package com.conduent.hcesdk.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

class RetrofitHelper {

    private static Retrofit retrofit;

    static Retrofit getClient() {
        if (retrofit != null) {
            return retrofit;
        }

        /*Below getUnsafeOkHttpClient need to replace with getOkHttpClient after api has valid certificates
         * Currently making By-pass of ssl*/

        final OkHttpClient okHttpClient = RetrofitProvider.getInstance().getUnSafeClient();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(RetrofitProvider.getInstance().getConfiguration().getBaseUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
