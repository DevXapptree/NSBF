package com.conduent.hcesdk.entities.remoteoffer.response;

public class ZonesPrices {
    private int UnitPrice;

    private String ZoneID;

    private String ZoneLabel;

    private int VatRate;

    public int getUnitPrice ()
    {
        return UnitPrice;
    }

    public void setUnitPrice (int UnitPrice)
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

    public int getVatRate ()
    {
        return VatRate;
    }

    public void setVatRate (int VatRate)
    {
        this.VatRate = VatRate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [UnitPrice = "+UnitPrice+", ZoneID = "+ZoneID+", ZoneLabel = "+ZoneLabel+", VatRate = "+VatRate+"]";
    }
}
