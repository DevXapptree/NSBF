package com.conduent.hcesdk.entities.result;

public class RContractValidityInfo {
    private String ContractEndDate;
    private String ContractStartDate;
    private String ContractZones;

    public RContractValidityInfo() {
    }

    public String getContractEndDate() {
        return ContractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        ContractEndDate = contractEndDate;
    }

    public String getContractStartDate() {
        return ContractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        ContractStartDate = contractStartDate;
    }

    public String getContractZones() {
        return ContractZones;
    }

    public void setContractZones(String contractZones) {
        ContractZones = contractZones;
    }
}
