package com.conduent.hcesdk.network;

import com.conduent.hcesdk.entities.remoteoffer.response.BuildMedia;
import com.conduent.hcesdk.entities.valuesapi.TestRes;
import com.conduent.hcesdk.entities.valuesapi.ValuesApiResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Apiservice {
    @GET("WS_SCONF/API/VALUES")
    Call<ValuesApiResponse> getValuesData();
    @GET("ip")
    Call<TestRes> getTestData();
    @POST("WS_VAD/sales/buildmedia")
    Call<BuildMedia> getBuildMedia(
            @Body RequestBody body
    );
}
