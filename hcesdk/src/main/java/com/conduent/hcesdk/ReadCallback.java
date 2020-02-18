package com.conduent.hcesdk;

public interface ReadCallback {
    void onReadComplete();

    void onReadError(HCEError error);
}
