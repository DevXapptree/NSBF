package com.conduent.hcesdk.entities.result;

import java.io.Serializable;

public class RCounter implements Serializable {
    private int CounterContractCount;
    private int CounterRelativeFirstStamp15Mn;

    public RCounter() {
    }

    public int getCounterContractCount() {
        return CounterContractCount;
    }

    public void setCounterContractCount(int counterContractCount) {
        CounterContractCount = counterContractCount;
    }

    public int getCounterRelativeFirstStamp15Mn() {
        return CounterRelativeFirstStamp15Mn;
    }

    public void setCounterRelativeFirstStamp15Mn(int counterRelativeFirstStamp15Mn) {
        CounterRelativeFirstStamp15Mn = counterRelativeFirstStamp15Mn;
    }

}
