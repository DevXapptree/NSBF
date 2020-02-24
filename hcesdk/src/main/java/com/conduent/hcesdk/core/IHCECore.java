package com.conduent.hcesdk.core;


import com.conduent.hcesdk.ReadCallback;
import com.conduent.hcesdk.ReadParameters;
import com.conduent.hcesdk.RetrieveRemoteOfferCallback;

public interface IHCECore {
    void startReading(final ReadParameters params, ReadCallback callback);

    void retrieveRemoteOffer(final ReadParameters params, RetrieveRemoteOfferCallback callback);
}
