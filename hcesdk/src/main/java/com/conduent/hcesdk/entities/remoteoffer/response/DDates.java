package com.conduent.hcesdk.entities.remoteoffer.response;

import java.io.Serializable;

public class DDates implements Serializable {
    private String MinStartDate;

    private int Duration;

    private int Step;

    private String Unit;

    private String MaxStartDate;

    public String getMinStartDate ()
    {
        return MinStartDate;
    }

    public void setMinStartDate (String MinStartDate)
    {
        this.MinStartDate = MinStartDate;
    }

    public int getDuration ()
    {
        return Duration;
    }

    public void setDuration (int Duration)
    {
        this.Duration = Duration;
    }

    public int getStep ()
    {
        return Step;
    }

    public void setStep (int Step)
    {
        this.Step = Step;
    }

    public String getUnit ()
    {
        return Unit;
    }

    public void setUnit (String Unit)
    {
        this.Unit = Unit;
    }

    public String getMaxStartDate ()
    {
        return MaxStartDate;
    }

    public void setMaxStartDate (String MaxStartDate)
    {
        this.MaxStartDate = MaxStartDate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MinStartDate = "+MinStartDate+", Duration = "+Duration+", Step = "+Step+", Unit = "+Unit+", MaxStartDate = "+MaxStartDate+"]";
    }
}
