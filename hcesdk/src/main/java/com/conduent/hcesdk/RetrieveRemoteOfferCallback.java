package com.conduent.hcesdk;

public interface RetrieveRemoteOfferCallback {
    void onRetrieveRemoteOffer();

    void onRetrieveRemoteOfferError(HCEError error);
}
