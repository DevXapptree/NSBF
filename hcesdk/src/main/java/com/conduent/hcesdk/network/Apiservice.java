package com.conduent.hcesdk.network;

import com.conduent.hcesdk.entities.valuesapi.TestRes;
import com.conduent.hcesdk.entities.valuesapi.ValuesApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Apiservice {
    @GET("WS_SCONF/API/VALUES")
    Call<ValuesApiResponse> getValuesData();
}
