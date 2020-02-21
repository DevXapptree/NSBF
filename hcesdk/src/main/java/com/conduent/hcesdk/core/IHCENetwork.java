package com.conduent.hcesdk.core;

import com.conduent.hcesdk.HCECardData;
import com.conduent.hcesdk.RetrieveRemoteOfferCallback;
import com.conduent.hcesdk.entities.remoteoffer.response.BuildMedia;

public interface IHCENetwork {

    void retrieveRemoteOfferApi(HCECardData hceCardData, RetrieveRemoteOfferCallback callback);

    void startProcessingRemoteOffer(BuildMedia body, RetrieveRemoteOfferCallback remoteOfferCallback);
}
