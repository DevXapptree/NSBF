package com.conduent.hcesdk.core;

import com.conduent.hcesdk.HCECardData;
import com.conduent.hcesdk.RetrieveRemoteOfferCallback;
import com.conduent.hcesdk.entities.remoteoffer.request.ContextWebApi;
import com.conduent.hcesdk.entities.remoteoffer.request.FiltersMediaManager;
import com.conduent.hcesdk.entities.remoteoffer.request.MediaInformations;
import com.conduent.hcesdk.entities.remoteoffer.request.RemoteRequest;

public class HCENetwork implements IHCENetwork{

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



    }
}
