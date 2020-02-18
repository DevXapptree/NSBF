package com.conduent.hcesdk.core;

import com.conduent.hcesdk.HCECardData;
import com.conduent.hcesdk.ReadCallback;
import com.conduent.hcesdk.RetrieveRemoteOfferCallback;

public interface ICoreParser {
    HCECardData parseStringToHCECardData(final String cardData);
    byte[] convertHexToByte(String hexData);
    void startParsingHCE(HCECardData hceCardData, ReadCallback readCallback);
    void startRemoteParsingHCE(HCECardData hceCardData, RetrieveRemoteOfferCallback callback);
}
