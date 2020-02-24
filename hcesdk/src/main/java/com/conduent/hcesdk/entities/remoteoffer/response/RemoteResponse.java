package com.conduent.hcesdk.entities.remoteoffer.response;

import com.conduent.hcesdk.entities.valuesapi.ProductCustomData;

import java.util.ArrayList;

public class RemoteResponse {
    private String ItemCategory;

    private String TitleOffer;

    private int UnitPrice;

    private Counter Counter;

    private String RequiresAuth;

    private ProductCustomData CustomData;

    private ArrayList<DatesZonesPrices> DatesZonesPrices;

    private int ArticleID;

    private int ProductID;

    private int NetworkID;

    private int VatRate;

    public String getItemCategory ()
    {
        return ItemCategory;
    }

    public void setItemCategory (String ItemCategory)
    {
        this.ItemCategory = ItemCategory;
    }

    public String getTitleOffer ()
    {
        return TitleOffer;
    }

    public void setTitleOffer (String TitleOffer)
    {
        this.TitleOffer = TitleOffer;
    }

    public int getUnitPrice ()
    {
        return UnitPrice;
    }

    public void setUnitPrice (int UnitPrice)
    {
        this.UnitPrice = UnitPrice;
    }

    public Counter getCounter ()
    {
        return Counter;
    }

    public void setCounter (Counter Counter)
    {
        this.Counter = Counter;
    }

    public String getRequiresAuth ()
    {
        return RequiresAuth;
    }

    public void setRequiresAuth (String RequiresAuth)
    {
        this.RequiresAuth = RequiresAuth;
    }

    public ProductCustomData getCustomData ()
    {
        return CustomData;
    }

    public void setCustomData (ProductCustomData CustomData)
    {
        this.CustomData = CustomData;
    }

    public ArrayList<DatesZonesPrices> getDatesZonesPrices ()
    {
        return DatesZonesPrices;
    }

    public void setDatesZonesPrices (ArrayList<DatesZonesPrices> DatesZonesPrices)
    {
        this.DatesZonesPrices = DatesZonesPrices;
    }

    public int getArticleID ()
    {
        return ArticleID;
    }

    public void setArticleID (int ArticleID)
    {
        this.ArticleID = ArticleID;
    }

    public int getProductID ()
    {
        return ProductID;
    }

    public void setProductID (int ProductID)
    {
        this.ProductID = ProductID;
    }

    public int getNetworkID ()
    {
        return NetworkID;
    }

    public void setNetworkID (int NetworkID)
    {
        this.NetworkID = NetworkID;
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
        return "ClassPojo [ItemCategory = "+ItemCategory+", TitleOffer = "+TitleOffer+", UnitPrice = "+UnitPrice+", Counter = "+Counter+", RequiresAuth = "+RequiresAuth+", CustomData = "+CustomData+", DatesZonesPrices = "+DatesZonesPrices+", ArticleID = "+ArticleID+", ProductID = "+ProductID+", NetworkID = "+NetworkID+", VatRate = "+VatRate+"]";
    }
}
