package com.conduent.hcesdk;

public interface RetrieveRemoteOfferCallback {

    void onStarted();

    void onReadTerminated();

    void onContractReceived(String data);

    void onError(Failure error, String message);

    void onTimeOut();
}
