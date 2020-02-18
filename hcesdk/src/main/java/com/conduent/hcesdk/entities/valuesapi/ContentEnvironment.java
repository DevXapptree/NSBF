package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;
import java.util.ArrayList;

public class ContentEnvironment implements Serializable {
    private EnvNetworkId envNetworkId;
    private ArrayList<Value> envApplicationIssuerId;
    private HolderData holderData;
    private ArrayList<Value> holderProfileNumber;

    public ContentEnvironment() {
    }

    public EnvNetworkId getEnvNetworkId() {
        return envNetworkId;
    }

    public void setEnvNetworkId(EnvNetworkId envNetworkId) {
        this.envNetworkId = envNetworkId;
    }

    public ArrayList<Value> getEnvApplicationIssuerId() {
        return envApplicationIssuerId;
    }

    public void setEnvApplicationIssuerId(ArrayList<Value> envApplicationIssuerId) {
        this.envApplicationIssuerId = envApplicationIssuerId;
    }

    public HolderData getHolderData() {
        return holderData;
    }

    public void setHolderData(HolderData holderData) {
        this.holderData = holderData;
    }

    public ArrayList<Value> getHolderProfileNumber() {
        return holderProfileNumber;
    }

    public void setHolderProfileNumber(ArrayList<Value> holderProfileNumber) {
        this.holderProfileNumber = holderProfileNumber;
    }
}
