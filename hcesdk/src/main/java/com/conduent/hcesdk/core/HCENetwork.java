package com.conduent.hcesdk.core;

import android.os.AsyncTask;
import android.util.Log;

import com.conduent.hcesdk.Failure;
import com.conduent.hcesdk.HCECardData;
import com.conduent.hcesdk.RetrieveRemoteOfferCallback;
import com.conduent.hcesdk.entities.remoteoffer.request.ContextWebApi;
import com.conduent.hcesdk.entities.remoteoffer.request.FiltersMediaManager;
import com.conduent.hcesdk.entities.remoteoffer.request.MediaInformations;
import com.conduent.hcesdk.entities.remoteoffer.request.RemoteRequest;
import com.conduent.hcesdk.entities.remoteoffer.response.BuildMedia;
import com.conduent.hcesdk.network.RetrofitConfig;
import com.conduent.hcesdk.network.ServiceGenerator;
import com.conduent.hcesdk.utils.HCEConstant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

class HCENetwork implements IHCENetwork{

    private RetrieveRemoteOfferCallback remoteOfferCallback;

    @Override
    public void retrieveRemoteOfferApi(HCECardData hceCardData, RetrieveRemoteOfferCallback callback) {
        this.remoteOfferCallback = callback;

        RemoteRequest remoteRequest = new RemoteRequest();

        remoteRequest.setFiltersMediaManager(new FiltersMediaManager());
        remoteRequest.setContextWebApi(new ContextWebApi());

        MediaInformations mediaInformations = new MediaInformations();

        mediaInformations.setMediaSerialNumber(String.valueOf(CoreParser.getInstance().startParsingMediaSerialNumber(hceCardData)));
        mediaInformations.setBuffersImage(CoreParser.getInstance().startRemoteParsingHCE(hceCardData));
        mediaInformations.setMediaUnicity(2);
        remoteRequest.setMediaInformations(mediaInformations);



        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new Gson()).toJson(remoteRequest));

        new RetrofitConfig.Builder().setBaseUrl(HCEConstant.BASE_URL).setTimeOut(HCEConstant.TIME_OUT).build();
        Call<BuildMedia> call = ServiceGenerator.Instance().getService().getBuildMedia(body);
        call.enqueue(new Callback<BuildMedia>() {
            @Override
            public void onResponse(Call<BuildMedia> call, Response<BuildMedia> response) {
                if(response.isSuccessful()){
                    startProcessingRemoteOffer(response.body(), remoteOfferCallback);
                }else{
                    remoteOfferCallback.onError(new Failure(response.message(), response.code()), response.message());
                }
            }

            @Override
            public void onFailure(Call<BuildMedia> call, Throwable t) {
                if(t.getCause() != null){

                    if(t instanceof HttpException){
                        HttpException exception = (HttpException) t;
                        Response response = exception.response();
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            Log.e("Error ","" + jsonObject.optString("message"));
                            remoteOfferCallback.onError(new Failure(response.message(), response.code()), response.message());
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }else{
                        remoteOfferCallback.onError(new Failure(t.getMessage(), call.hashCode()), t.getMessage());
                    }
                }
            }
        });

    }

    @Override
    public void startProcessingRemoteOffer(BuildMedia mediaData, RetrieveRemoteOfferCallback remoteOfferCallback) {
        new RetrieveRemoteOfferAsync(mediaData, remoteOfferCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
