package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class EnvNetworkId implements Serializable {
    private Value country;
    private Value network;

    public EnvNetworkId() {
    }

    public Value getCountry() {
        return country;
    }

    public void setCountry(Value country) {
        this.country = country;
    }

    public Value getNetwork() {
        return network;
    }

    public void setNetwork(Value network) {
        this.network = network;
    }
}
