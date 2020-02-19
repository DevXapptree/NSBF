package com.conduent.hcesdk.entities.remoteoffer.request;

import com.conduent.hcesdk.utils.HCEUtils;

import java.io.Serializable;

public class AfterSaleRefundFilter implements Serializable {

    private String RequestDate;

    private String RefundType;

    public AfterSaleRefundFilter(){
        RefundType = "Undefined";
        RequestDate = HCEUtils.getCurrentUCTTime();
    }

    public String getRequestDate ()
    {
        return RequestDate;
    }

    public void setRequestDate (String RequestDate)
    {
        this.RequestDate = RequestDate;
    }

    public String getRefundType ()
    {
        return RefundType;
    }

    public void setRefundType (String RefundType)
    {
        this.RefundType = RefundType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [RequestDate = "+RequestDate+", RefundType = "+RefundType+"]";
    }
}
