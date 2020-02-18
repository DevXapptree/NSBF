package com.conduent.hcesdk.core;

import android.util.Log;
import com.conduent.hcesdk.*;
import com.conduent.hcesdk.utils.HCEConstant;

final class HCECore implements IHCECore {

    private String TAG = "NSBF-SDK---";
    private HCECardData hceCardData = null;

    @Override
    public void startReading(HCECardData hceCardData, ReadCallback callback) {
        if (hceCardData == null)
            callback.onReadError(new HCEError("", HCEConstant.HCEErrorCodes.DATA_NULL.ordinal(), null));
        if (callback == null)
            throw new RuntimeException("Required ReadCallback, interface can not be null!");
        this.hceCardData = hceCardData;
    }

    @Override
    public void startReading(String hceCardData, ReadCallback callback) {
        HCECardData cardData = CoreParser.getInstance().parseStringToHCECardData(hceCardData);
        if (cardData == null)
            callback.onReadError(new HCEError("Data not found.", HCEConstant.HCEErrorCodes.DATA_NULL.ordinal(), null));
        if (callback == null)
            throw new RuntimeException("Required ReadCallback, interface can not be null!");
        this.hceCardData = cardData;
    }

    @Override
    public void startReading(ReadParameters params, ReadCallback callback) {
        if (params == null) {
            callback.onReadError(new HCEError("ReadParameters not found.", HCEConstant.HCEErrorCodes.DATA_NULL.ordinal(), null));
            return;
        }
        if (callback == null)
            throw new RuntimeException("startReading required ReadCallback, interface ReadCallback can not be null!");

        if (params.getSourceType() == SourceType.HCE) {
            HCECardData cardData = CoreParser.getInstance().parseStringToHCECardData(params.getData());
            if (cardData == null)
                callback.onReadError(new HCEError("Data not found.", HCEConstant.HCEErrorCodes.DATA_NULL.ordinal(), null));

            this.hceCardData = cardData;
            /*After parsing send result ReadCallback*/
            CoreParser.getInstance().startParsingHCE(hceCardData, callback);
        }
    }

    @Override
    public void retrieveRemoteOffer() {
        Log.i(TAG, "retrieveRemoteOffer");
    }

    @Override
    public void retrieveRemoteOffer(ReadParameters params, RetrieveRemoteOfferCallback callback) {
        if (params == null) {
            callback.onRetrieveRemoteOfferError(new HCEError("ReadParameters not found.", HCEConstant.HCEErrorCodes.DATA_NULL.ordinal(), null));
            return;
        }
        if(callback == null)
            throw new RuntimeException("startReading required ReadCallback, interface ReadCallback can not be null!");

        if (params.getSourceType() == SourceType.HCE) {
            HCECardData cardData = CoreParser.getInstance().parseStringToHCECardData(params.getData());
            if (cardData == null)
                callback.onRetrieveRemoteOfferError(new HCEError("Data not found.", HCEConstant.HCEErrorCodes.DATA_NULL.ordinal(),null));

            this.hceCardData = cardData;
            CoreParser.getInstance().startRemoteParsingHCE(hceCardData, callback);
        }
    }
}
