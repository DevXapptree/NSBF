package com.conduent.hcesdk.core;

import android.content.Context;
import com.conduent.hcesdk.ReadCallback;

public interface IHCEEngine extends IHCECore {
    void pingMe(ReadCallback callback);
    Context getContext();
}
