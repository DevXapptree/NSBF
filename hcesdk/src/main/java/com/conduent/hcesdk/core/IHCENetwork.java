package com.conduent.hcesdk.core;

import com.conduent.hcesdk.HCECardData;
import com.conduent.hcesdk.RetrieveRemoteOfferCallback;

public interface IHCENetwork {

    void retrieveRemoteOfferApi(HCECardData hceCardData, RetrieveRemoteOfferCallback callback);
}
