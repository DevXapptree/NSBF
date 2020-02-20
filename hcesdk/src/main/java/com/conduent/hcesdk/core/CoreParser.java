package com.conduent.hcesdk.core;

import com.conduent.hcesdk.HCECardData;
import com.conduent.hcesdk.ReadCallback;
import com.conduent.hcesdk.utils.HCEUtils;
import com.google.gson.Gson;

class CoreParser implements ICoreParser {

    private static volatile ICoreParser instance;
    //FIXME Currently no usage, will remove once step2 in done.
    private ReadCallback readCallback;

    private CoreParser() {

    }

    static ICoreParser getInstance() {
        if (instance == null) {
            synchronized (CoreParser.class) {
                if (instance == null) {
                    instance = new CoreParser();
                }
            }
        }
        return instance;
    }

    @Override
    public HCECardData parseStringToHCECardData(String cardData) {
        HCECardData hceCardData = null;
        try {
            hceCardData = new Gson().fromJson(cardData, HCECardData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return hceCardData;
    }

    @Override
    public void startParsingHCE(HCECardData hceCardData, ReadCallback readCallback) {
        this.readCallback = readCallback;
        /*Execute HCEParseAsync*/
        new HCEParseAsync(hceCardData, readCallback).execute();
    }

    @Override
    public String convertHexToBinary(String hexData) {
        return HCEUtils.HexStringToBinaryString(hexData);
    }
}
