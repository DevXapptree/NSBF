package com.conduent.hcesdk;

public interface RetrieveRemoteOfferCallback {
//    void onRetrieveRemoteOffer();
//
//    void onRetrieveRemoteOfferError(Failure error);

//    void onStarted();
//
//    void onEnded();
//
//    void onError(Failure error, String message);
//
//    void onTimeOut();

    void onStarted();

    void onReadTerminated();

    void onContractReceived(String data);

    void onError(Failure error, String message);

    void onTimeOut();
}
