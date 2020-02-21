package com.conduent.hcesdk;

import android.os.IBinder;
import com.conduent.hcesdk.entities.result.HCECardResult;

public interface ReadCallback {
    void onStarted();
    void onEnded(String cardParsedContent);
    //void onReadComplete(HCECardResult result);
    void onError(Failure error);
    void onTimeOut();
}
