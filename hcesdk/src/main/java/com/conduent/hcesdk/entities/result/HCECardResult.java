package com.conduent.hcesdk.entities.result;

import java.util.ArrayList;

public class HCECardResult {
    private ArrayList<ContractResult> Contracts;
    private ArrayList<EnvironmentResult> Environments;

    private ArrayList<TransportLogResult> Events;

    public HCECardResult() {
    }

    public ArrayList<ContractResult> getContracts() {
        return Contracts;
    }

    public void setContracts(ArrayList<ContractResult> contracts) {
        Contracts = contracts;
    }

    public ArrayList<EnvironmentResult> getEnvironments() {
        return Environments;
    }

    public void setEnvironments(ArrayList<EnvironmentResult> environments) {
        Environments = environments;
    }

    public ArrayList<TransportLogResult> getEvents() {
        return Events;
    }

    public void setEvents(ArrayList<TransportLogResult> events) {
        Events = events;
    }
}
