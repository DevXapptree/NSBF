package com.conduent.hcesdk.core;

import android.app.Activity;
import android.content.Context;
import com.conduent.hcesdk.ReadCallback;

public interface IHCEEngine extends IHCECore {
    void pingMe(ReadCallback callback);
    String GetBuffers(Activity activity);
    Context getContext();
}
