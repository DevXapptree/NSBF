package com.conduent.hcesdk.entities.remoteoffer.response;

import java.util.ArrayList;

public class DatesZonesPrices {
    private ArrayList<ZonesPrices> ZonesPrices;

    private ArrayList<DDates> Dates;

    public ArrayList<ZonesPrices> getZonesPrices ()
    {
        return ZonesPrices;
    }

    public void setZonesPrices (ArrayList<ZonesPrices> ZonesPrices)
    {
        this.ZonesPrices = ZonesPrices;
    }

    public ArrayList<DDates> getDates ()
    {
        return Dates;
    }

    public void setDates (ArrayList<DDates> Dates)
    {
        this.Dates = Dates;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ZonesPrices = "+ZonesPrices+", DDates = "+Dates+"]";
    }
}
