package com.conduent.hcesdk;

public interface RetrieveRemoteOfferCallback {
//    void onRetrieveRemoteOffer();
//
//    void onRetrieveRemoteOfferError(Failure error);

    void onStarted();

    void onEnded();

    void onError(Failure error, String message);

    void onTimeOut();
}
