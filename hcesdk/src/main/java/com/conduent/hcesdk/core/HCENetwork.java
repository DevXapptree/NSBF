package com.conduent.hcesdk.core;

import android.util.Log;
import com.conduent.hcesdk.HCECardData;
import com.conduent.hcesdk.RetrieveRemoteOfferCallback;
import com.conduent.hcesdk.entities.remoteoffer.request.ContextWebApi;
import com.conduent.hcesdk.entities.remoteoffer.request.FiltersMediaManager;
import com.conduent.hcesdk.entities.remoteoffer.request.MediaInformations;
import com.conduent.hcesdk.entities.remoteoffer.request.RemoteRequest;
import com.conduent.hcesdk.entities.valuesapi.ValuesApiResponse;
import com.conduent.hcesdk.network.ServiceGenerator;
import com.conduent.hcesdk.room.DatabaseQueryAsync;
import com.conduent.hcesdk.room.RoomRequestCodes;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HCENetwork implements IHCENetwork {

    private RetrieveRemoteOfferCallback remoteOfferCallback;

    @Override
    public void retrieveValues() {
        Call<ValuesApiResponse> call = ServiceGenerator.Instance().getService().getValuesData();
        call.enqueue(new Callback<ValuesApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<ValuesApiResponse> call, @NotNull Response<ValuesApiResponse> response) {
                Log.i("NSBF", "Succes");
                if (response.isSuccessful()) {
                    ValuesApiResponse valuesApiResponse = response.body();
                    new DatabaseQueryAsync(HCEEngine.localInstance().getContext(), RoomRequestCodes.INSERT_VALUES_API_FILE, valuesApiResponse).execute();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ValuesApiResponse> call, @NotNull Throwable t) {
                Log.i("NSBF", "fail");
                Log.i("NSBF", t.getMessage());
                /*Fetching Api data failed, so storing data from ValuesApi.json from assets*/
                IMappingRule mapRuleAccess = CoreProvider.getInstance().provideMappingRuleAccess();
                ValuesApiResponse valuesApiResponse = mapRuleAccess.provideValuesApiResponse();
                new DatabaseQueryAsync(HCEEngine.localInstance().getContext(), RoomRequestCodes.INSERT_VALUES_API_FILE, valuesApiResponse).execute();
            }
        });
    }

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

    }
}
