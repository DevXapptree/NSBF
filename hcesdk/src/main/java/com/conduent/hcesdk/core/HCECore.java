package com.conduent.hcesdk.core;

import com.conduent.hcesdk.*;
import com.conduent.hcesdk.utils.HCEConstant;

final class HCECore implements IHCECore {

    private HCECardData hceCardData = null;

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
            /*Start parsing*/
            CoreParser.getInstance().startParsingHCE(hceCardData, callback);
        }
    }

    @Override
    public void retrieveRemoteOffer() {
    }
}
