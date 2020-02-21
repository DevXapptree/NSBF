package com.conduent.hcesdk.entities.remoteoffer.response;

public class ZonesPrices {
    private String UnitPrice;

    private String ZoneID;

    private String ZoneLabel;

    private String VatRate;

    public String getUnitPrice ()
    {
        return UnitPrice;
    }

    public void setUnitPrice (String UnitPrice)
    {
        this.UnitPrice = UnitPrice;
    }

    public String getZoneID ()
    {
        return ZoneID;
    }

    public void setZoneID (String ZoneID)
    {
        this.ZoneID = ZoneID;
    }

    public String getZoneLabel ()
    {
        return ZoneLabel;
    }

    public void setZoneLabel (String ZoneLabel)
    {
        this.ZoneLabel = ZoneLabel;
    }

    public String getVatRate ()
    {
        return VatRate;
    }

    public void setVatRate (String VatRate)
    {
        this.VatRate = VatRate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [UnitPrice = "+UnitPrice+", ZoneID = "+ZoneID+", ZoneLabel = "+ZoneLabel+", VatRate = "+VatRate+"]";
    }
}
