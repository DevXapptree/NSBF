package com.conduent.hcesdk.entities.remoteoffer.response;

public class CustomData {
    private String CustomData2;

    private String CustomData1;

    private String CustomData4;

    private String CustomData3;

    public String getCustomData2 ()
    {
        return CustomData2;
    }

    public void setCustomData2 (String CustomData2)
    {
        this.CustomData2 = CustomData2;
    }

    public String getCustomData1 ()
    {
        return CustomData1;
    }

    public void setCustomData1 (String CustomData1)
    {
        this.CustomData1 = CustomData1;
    }

    public String getCustomData4 ()
    {
        return CustomData4;
    }

    public void setCustomData4 (String CustomData4)
    {
        this.CustomData4 = CustomData4;
    }

    public String getCustomData3 ()
    {
        return CustomData3;
    }

    public void setCustomData3 (String CustomData3)
    {
        this.CustomData3 = CustomData3;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [CustomData2 = "+CustomData2+", CustomData1 = "+CustomData1+", CustomData4 = "+CustomData4+", CustomData3 = "+CustomData3+"]";
    }
}
