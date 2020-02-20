package com.conduent.hcesdk.core;

import com.conduent.hcesdk.HCECardData;
import com.conduent.hcesdk.ReadCallback;

public interface ICoreParser {
    HCECardData parseStringToHCECardData(final String cardData);
    String convertHexToBinary(String hexData);
    void startParsingHCE(HCECardData hceCardData, ReadCallback readCallback);
}
