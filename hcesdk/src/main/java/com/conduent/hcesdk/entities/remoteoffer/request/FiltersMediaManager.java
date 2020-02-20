package com.conduent.hcesdk.entities.remoteoffer.request;

import java.io.Serializable;

public class FiltersMediaManager implements Serializable {
    private AfterSaleRefundFilter AfterSaleRefundFilter;

    private boolean IsFilterSalesDetailsEnable;

    private boolean IsFilterPendingOperationsEnable;

    private boolean IsFilterAfterSaleEnable;

    private boolean IsMediaInfoWebApiDetails;

    public FiltersMediaManager(){
        IsFilterSalesDetailsEnable = true;
        IsFilterPendingOperationsEnable = true;
        IsFilterAfterSaleEnable = true;
        IsMediaInfoWebApiDetails = true;
        AfterSaleRefundFilter = new AfterSaleRefundFilter();
    }

    public AfterSaleRefundFilter getAfterSaleRefundFilter ()
    {
        return AfterSaleRefundFilter;
    }

    public void setAfterSaleRefundFilter (AfterSaleRefundFilter AfterSaleRefundFilter)
    {
        this.AfterSaleRefundFilter = AfterSaleRefundFilter;
    }

    public boolean getIsFilterSalesDetailsEnable ()
    {
        return IsFilterSalesDetailsEnable;
    }

    public void setIsFilterSalesDetailsEnable (boolean IsFilterSalesDetailsEnable)
    {
        this.IsFilterSalesDetailsEnable = IsFilterSalesDetailsEnable;
    }

    public boolean getIsFilterPendingOperationsEnable ()
    {
        return IsFilterPendingOperationsEnable;
    }

    public void setIsFilterPendingOperationsEnable (boolean IsFilterPendingOperationsEnable)
    {
        this.IsFilterPendingOperationsEnable = IsFilterPendingOperationsEnable;
    }

    public boolean getIsFilterAfterSaleEnable ()
    {
        return IsFilterAfterSaleEnable;
    }

    public void setIsFilterAfterSaleEnable (boolean IsFilterAfterSaleEnable)
    {
        this.IsFilterAfterSaleEnable = IsFilterAfterSaleEnable;
    }

    public boolean getIsMediaInfoWebApiDetails ()
    {
        return IsMediaInfoWebApiDetails;
    }

    public void setIsMediaInfoWebApiDetails (boolean IsMediaInfoWebApiDetails)
    {
        this.IsMediaInfoWebApiDetails = IsMediaInfoWebApiDetails;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [AfterSaleRefundFilter = "+AfterSaleRefundFilter+", IsFilterSalesDetailsEnable = "+IsFilterSalesDetailsEnable+", IsFilterPendingOperationsEnable = "+IsFilterPendingOperationsEnable+", IsFilterAfterSaleEnable = "+IsFilterAfterSaleEnable+", IsMediaInfoWebApiDetails = "+IsMediaInfoWebApiDetails+"]";
    }
}
