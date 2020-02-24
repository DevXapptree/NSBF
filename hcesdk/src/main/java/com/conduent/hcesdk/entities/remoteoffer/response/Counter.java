package com.conduent.hcesdk.entities.remoteoffer.response;

import java.io.Serializable;

public class Counter implements Serializable {
    private int MaxBuyValue;

    private int MinBuyValue;

    private int StepBuyValue;

    public Counter(){

    }

    public int getMaxBuyValue ()
    {
        return MaxBuyValue;
    }

    public void setMaxBuyValue (int MaxBuyValue)
    {
        this.MaxBuyValue = MaxBuyValue;
    }

    public int getMinBuyValue ()
    {
        return MinBuyValue;
    }

    public void setMinBuyValue (int MinBuyValue)
    {
        this.MinBuyValue = MinBuyValue;
    }

    public int getStepBuyValue ()
    {
        return StepBuyValue;
    }

    public void setStepBuyValue (int StepBuyValue)
    {
        this.StepBuyValue = StepBuyValue;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MaxBuyValue = "+MaxBuyValue+", MinBuyValue = "+MinBuyValue+", StepBuyValue = "+StepBuyValue+"]";
    }
}
