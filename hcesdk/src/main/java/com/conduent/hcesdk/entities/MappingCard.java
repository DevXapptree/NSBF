package com.conduent.hcesdk.entities;

public class MappingCard {
    private Environment environment;
    private Holder Holder;
    private Contract contracts;
    private ContractList ContractsList;
    private TransportLog TransportLog;

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public com.conduent.hcesdk.entities.Holder getHolder() {
        return Holder;
    }

    public void setHolder(com.conduent.hcesdk.entities.Holder holder) {
        Holder = holder;
    }

    public Contract getContracts() {
        return contracts;
    }

    public void setContracts(Contract contracts) {
        this.contracts = contracts;
    }

    public ContractList getContractsList() {
        return ContractsList;
    }

    public void setContractsList(ContractList contractsList) {
        ContractsList = contractsList;
    }

    public com.conduent.hcesdk.entities.TransportLog getTransportLog() {
        return TransportLog;
    }

    public void setTransportLog(com.conduent.hcesdk.entities.TransportLog transportLog) {
        TransportLog = transportLog;
    }

    public MappingCard() {

    }

}
