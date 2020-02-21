package com.conduent.hcesdk.entities.remoteoffer.response;

public class Counter {
    private String MaxBuyValue;

    private String MinBuyValue;

    private String StepBuyValue;

    public String getMaxBuyValue ()
    {
        return MaxBuyValue;
    }

    public void setMaxBuyValue (String MaxBuyValue)
    {
        this.MaxBuyValue = MaxBuyValue;
    }

    public String getMinBuyValue ()
    {
        return MinBuyValue;
    }

    public void setMinBuyValue (String MinBuyValue)
    {
        this.MinBuyValue = MinBuyValue;
    }

    public String getStepBuyValue ()
    {
        return StepBuyValue;
    }

    public void setStepBuyValue (String StepBuyValue)
    {
        this.StepBuyValue = StepBuyValue;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MaxBuyValue = "+MaxBuyValue+", MinBuyValue = "+MinBuyValue+", StepBuyValue = "+StepBuyValue+"]";
    }
}
