package com.conduent.hcesdk.core;

import com.conduent.hcesdk.*;
import com.conduent.hcesdk.utils.HCEConstant;

final class HCECore implements IHCECore {

    private HCECardData hceCardData = null;

    @Override
    public void startReading(ReadParameters params, ReadCallback callback) {
        if (params == null) {
            callback.onError(new Failure("ReadParameters not found.", HCEConstant.HCEErrorCodes.DATA_NULL.ordinal()));
            return;
        }

        if (callback == null)
            throw new RuntimeException("startReading required ReadCallback, interface ReadCallback can not be null!");

        if (params.getSourceType() == SourceType.HCE) {
            HCECardData cardData = CoreParser.getInstance().parseStringToHCECardData(params.getData());
            if (cardData == null) {
                callback.onError(new Failure("Data not found.", HCEConstant.HCEErrorCodes.DATA_NULL.ordinal()));
                return;
            }

            this.hceCardData = cardData;
            /*Start parsing*/
            CoreParser.getInstance().startParsingHCE(hceCardData, callback);
        }
    }

    @Override
    public void retrieveRemoteOffer(ReadParameters params, RetrieveRemoteOfferCallback callback) {
        if (params == null) {
            callback.onError(new Failure("ReadParameters not found.", HCEConstant.HCEErrorCodes.DATA_NULL.ordinal()), "ReadParameters not found.");
            return;
        }
        if (callback == null){
            throw new RuntimeException("retrieveRemoteOffer required RetrieveRemoteOfferCallback, interface RetrieveRemoteOfferCallback can not be null!");
        }

        if (params.getSourceType() == SourceType.HCE) {
            HCECardData cardData = CoreParser.getInstance().parseStringToHCECardData(params.getData());
            if (cardData == null) {
                callback.onError(new Failure("Data not found.", HCEConstant.HCEErrorCodes.DATA_NULL.ordinal()), "Data not found.");
                return;
            }

            this.hceCardData = cardData;
            CoreProvider.getInstance().provideHCENetworkAccess().retrieveRemoteOfferApi(hceCardData, callback);
        }
    }

}
