package com.conduent.hcesdk.entities.result;

import com.conduent.hcesdk.entities.valuesapi.ProductCustomData;
import com.conduent.hcesdk.entities.valuesapi.ProductDescription;

import java.io.Serializable;

public class ContractResult implements Serializable {
    private int ContractAuthenticator;
    private ProductCustomData ContractCustomData;
    private ProductDescription ContractDescriptions;
    private String ContractPayMethod;
    private String ContractPriceAmount;
    private int ContractProvider;
    private RContractSaleData ContractSaleData;
    private String ContractStatus;
    private int ContractTariff;
    private RContractValidityInfo ContractValidityInfo;
    private RCounter Counter;
    private int Priority;
    private int RecordNumber;

    public ContractResult() {
    }

    public int getContractAuthenticator() {
        return ContractAuthenticator;
    }

    public void setContractAuthenticator(int contractAuthenticator) {
        ContractAuthenticator = contractAuthenticator;
    }

    public ProductCustomData getContractCustomData() {
        return ContractCustomData;
    }

    public void setContractCustomData(ProductCustomData contractCustomData) {
        ContractCustomData = contractCustomData;
    }

    public ProductDescription getContractDescriptions() {
        return ContractDescriptions;
    }

    public void setContractDescriptions(ProductDescription contractDescriptions) {
        ContractDescriptions = contractDescriptions;
    }

    public String getContractPayMethod() {
        return ContractPayMethod;
    }

    public void setContractPayMethod(String contractPayMethod) {
        ContractPayMethod = contractPayMethod;
    }

    public String getContractPriceAmount() {
        return ContractPriceAmount;
    }

    public void setContractPriceAmount(String contractPriceAmount) {
        ContractPriceAmount = contractPriceAmount;
    }

    public int getContractProvider() {
        return ContractProvider;
    }

    public void setContractProvider(int contractProvider) {
        ContractProvider = contractProvider;
    }

    public RContractSaleData getContractSaleData() {
        return ContractSaleData;
    }

    public void setContractSaleData(RContractSaleData contractSaleData) {
        ContractSaleData = contractSaleData;
    }

    public String getContractStatus() {
        return ContractStatus;
    }

    public void setContractStatus(String contractStatus) {
        ContractStatus = contractStatus;
    }

    public int getContractTariff() {
        return ContractTariff;
    }

    public void setContractTariff(int contractTariff) {
        ContractTariff = contractTariff;
    }

    public RContractValidityInfo getContractValidityInfo() {
        return ContractValidityInfo;
    }

    public void setContractValidityInfo(RContractValidityInfo contractValidityInfo) {
        ContractValidityInfo = contractValidityInfo;
    }

    public RCounter getCounter() {
        return Counter;
    }

    public void setCounter(RCounter counter) {
        Counter = counter;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public int getRecordNumber() {
        return RecordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        RecordNumber = recordNumber;
    }
}
