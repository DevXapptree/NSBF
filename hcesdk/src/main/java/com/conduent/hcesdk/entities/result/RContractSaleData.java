package com.conduent.hcesdk.entities.result;

public class RContractSaleData {
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
