package com.conduent.hcesdk.core;


import android.app.Activity;
import android.content.Context;
import com.arjosystems.sdkalemu.model.MediaInformations;
import com.arjosystems.sdkalemu.model.SdkResponse;
import com.arjosystems.sdkalemu.rest.HCEMidlet;
import com.arjosystems.sdkalemu.rest.dto.ParamInfo;
import com.conduent.hcesdk.R;
import com.conduent.hcesdk.ReadCallback;
import com.conduent.hcesdk.ReadParameters;
import com.conduent.hcesdk.RetrieveRemoteOfferCallback;
import com.conduent.hcesdk.network.RetrofitConfig;
import com.conduent.hcesdk.utils.HCEConstant;
import com.conduent.hcesdk.utils.PreConditions;
import com.google.gson.Gson;

public class HCEEngine implements IHCEEngine {

    private static volatile IHCEEngine instance;
    private Context context;

    private HCEEngine(final Context context) {
        this.context = context;
    }

    public static IHCEEngine getInstance() {
        HCEEngine defaultApp = (HCEEngine) instance;
        synchronized (HCEEngine.class) {
            if (defaultApp == null) {
                throw new IllegalStateException("Default HCEEngine is not initialized. Make sure to call HCEEngine.initializeApp(Context) first.");
            } else {
                return defaultApp;
            }
        }
    }

    public static void initializeApp(Context context) {

        Context applicationContext;
        if (context.getApplicationContext() == null) {
            applicationContext = context;
        } else {
            applicationContext = context.getApplicationContext();
        }

        HCEEngine hceEngine;
        synchronized (HCEEngine.class) {
            PreConditions.checkNotNull(applicationContext, "Application context cannot be null.");
            hceEngine = new HCEEngine(applicationContext);
            instance = hceEngine;
        }
        hceEngine.initConfiguration();
    }

    static IHCEEngine localInstance() {
        return instance;
    }

    /*StartReading of HCE card*/
    @Override
    public void startReading(ReadParameters params, ReadCallback callback) {
        getHCEAccess().startReading(params, callback);
    }

    /*pingMe is for Demo purpose*/
    @Override
    public void retrieveRemoteOffer(ReadParameters params, RetrieveRemoteOfferCallback callback) {
        getHCEAccess().retrieveRemoteOffer(params, callback);
    }

    @Override
    public void pingMe(ReadCallback callback) {
        callback.onEnded("Ping Success");
    }

    @Override
    public String GetBuffers(Activity activity) {
        HCEMidlet midlet = com.arjosystems.sdkalemu.rest.HCEEngine.getInstance(activity);
        ParamInfo oParamInfo = new ParamInfo();
        oParamInfo.setHceServerURL(activity.getString(R.string.hce_server_url));
        midlet.configure(oParamInfo);
        String installResponse = midlet.poc_installNavigo(false);

       // HCEMidlet midlet = com.arjosystems.sdkalemu.rest.HCEEngine.getInstance(activity);
        SdkResponse<MediaInformations> response = midlet.getBuffers("mCardId");
        MediaInformations media = response.getPayload();
        return new Gson().toJson(media);
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

    /*Getting access to make use of HCENetwork class*/
    private IHCENetwork getNetworkAccess() {
        return CoreProvider.getInstance().provideHCENetworkAccess();
    }

    private void initConfiguration() {
        new RetrofitConfig.Builder().setBaseUrl(HCEConstant.BASE_URL).setTimeOut(HCEConstant.TIME_OUT).build();
        getNetworkAccess().retrieveValues();
    }
}
