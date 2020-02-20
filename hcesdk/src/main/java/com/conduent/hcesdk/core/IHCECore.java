package com.conduent.hcesdk.core;


import com.conduent.hcesdk.ReadCallback;
import com.conduent.hcesdk.ReadParameters;

public interface IHCECore {
    void startReading(final ReadParameters params, ReadCallback callback);
    void retrieveRemoteOffer();
}
