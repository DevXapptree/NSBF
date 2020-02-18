package com.conduent.hcesdk.core;


import com.conduent.hcesdk.HCECardData;
import com.conduent.hcesdk.ReadCallback;
import com.conduent.hcesdk.ReadParameters;

public interface IHCECore {
    void startReading(final HCECardData hceCardData, ReadCallback callback);

    void startReading(final String hceCardData, ReadCallback callback);

    void startReading(final ReadParameters params, ReadCallback callback);

    void retrieveRemoteOffer();
}
