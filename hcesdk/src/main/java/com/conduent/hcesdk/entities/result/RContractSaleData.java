package com.conduent.hcesdk.entities.result;

import java.io.Serializable;

public class RContractSaleData implements Serializable {
    private int ContractSaleAgent;
    private String ContractSaleDate;
    private int ContractSaleDevice;

    public RContractSaleData() {
    }

    public int getContractSaleAgent() {
        return ContractSaleAgent;
    }

    public void setContractSaleAgent(int contractSaleAgent) {
        ContractSaleAgent = contractSaleAgent;
    }

    public String getContractSaleDate() {
        return ContractSaleDate;
    }

    public void setContractSaleDate(String contractSaleDate) {
        ContractSaleDate = contractSaleDate;
    }

    public int getContractSaleDevice() {
        return ContractSaleDevice;
    }

    public void setContractSaleDevice(int contractSaleDevice) {
        ContractSaleDevice = contractSaleDevice;
    }
}
