package com.conduent.hcesdk.entities.remoteoffer.response;

public class DDates {
    private String MinStartDate;

    private String Duration;

    private String Step;

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

    public String getDuration ()
    {
        return Duration;
    }

    public void setDuration (String Duration)
    {
        this.Duration = Duration;
    }

    public String getStep ()
    {
        return Step;
    }

    public void setStep (String Step)
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
