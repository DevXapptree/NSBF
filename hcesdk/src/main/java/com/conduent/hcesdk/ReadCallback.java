package com.conduent.hcesdk;

import com.conduent.hcesdk.entities.result.HCECardResult;

public interface ReadCallback {
    void onReadComplete(HCECardResult result);

    void onReadError(HCEError error);

    void onError();
}
