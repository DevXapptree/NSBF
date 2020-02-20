package com.conduent.hcesdk;

public interface RetrieveRemoteOfferCallback {
    void onRetrieveRemoteOffer();

    void onRetrieveRemoteOfferError(Failure error);
}
