package com.conduent.hcesdk.core;


import android.content.Context;
import android.util.Log;
import com.conduent.hcesdk.ReadCallback;
import com.conduent.hcesdk.ReadParameters;
import com.conduent.hcesdk.RetrieveRemoteOfferCallback;
import com.conduent.hcesdk.entities.valuesapi.ValuesApiResponse;
import com.conduent.hcesdk.network.RetrofitConfig;
import com.conduent.hcesdk.network.ServiceGenerator;
import com.conduent.hcesdk.room.DatabaseQueryAsync;
import com.conduent.hcesdk.room.OnDataBaseQueryListener;
import com.conduent.hcesdk.room.RoomRequestCodes;
import com.conduent.hcesdk.utils.HCEConstant;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HCEEngine implements IHCEEngine {

    private static volatile IHCEEngine instance;
    private Context context;

    private HCEEngine(final Context context) {
        this.context = context;
        new RetrofitConfig.Builder().setBaseUrl(HCEConstant.BASE_URL).setTimeOut(HCEConstant.TIME_OUT).build();
    }

    public static IHCEEngine getInstance(Context context) {
        if (instance == null) {
            synchronized (HCEEngine.class) {
                if (instance == null)
                    instance = new HCEEngine(context);
            }
        }
        return instance;
    }

    static IHCEEngine localInstance() {
        return instance;
    }

    /*StartReading of HCE card*/
    @Override
    public void startReading(ReadParameters params, ReadCallback callback) {
        getHCEAccess().startReading(params, callback);
    }

    @Override
    public void retrieveRemoteOffer() {
        new DatabaseQueryAsync(localInstance().getContext(), RoomRequestCodes.GET_VERSION_FILE, new OnDataBaseQueryListener() {
            @Override
            public void onDataFetched(@NotNull Object singleFile) {
                Log.i("NSBF", "Success");
            }

            @Override
            public void onCountFetched(int count) {
                Log.i("NSBF", "count");
            }
        }).execute();
    }

    /*pingMe is for Demo purpose*/
    @Override
    public void retrieveRemoteOffer(ReadParameters params, RetrieveRemoteOfferCallback callback) {
        getHCEAccess().retrieveRemoteOffer(params, callback);
    }

    @Override
    public void pingMe(ReadCallback callback) {
        //callback.onReadComplete();
        IMappingRule mapRuleAccess = CoreProvider.getInstance().provideMappingRuleAccess();
        ValuesApiResponse valuesApiResponse = mapRuleAccess.provideValuesApiResponse();
        new DatabaseQueryAsync(localInstance().getContext(), RoomRequestCodes.INSERT_VALUES_API_FILE, valuesApiResponse).execute();
    }

    /*Provide local context to get use of android resources*/
    @Override
    public Context getContext() {
        return this.context;
    }

    /*Getting access to make use of HCECore class*/
    private IHCECore getHCEAccess() {
        return CoreProvider.getInstance().provideHCECoreAccess();
    }

    /*FIXME Remove once done with HCENetwork Class implementation.*/
    private void makeRetroCall() {
        new RetrofitConfig.Builder().setBaseUrl(HCEConstant.BASE_URL).setTimeOut(HCEConstant.TIME_OUT).build();
        Call<ValuesApiResponse> call = ServiceGenerator.Instance().getService().getValuesData();
        call.enqueue(new Callback<ValuesApiResponse>() {
            @Override
            public void onResponse(Call<ValuesApiResponse> call, Response<ValuesApiResponse> response) {
                Log.i("NSBF", "Succes");
            }

            @Override
            public void onFailure(Call<ValuesApiResponse> call, Throwable t) {
                Log.i("NSBF", "fail");
            }
        });
    }
}
