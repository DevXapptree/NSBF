package com.conduent.hcesdk.entities.result;

public class EnvironmentResult {
    private int EnvVersionNumber;
    private REnvironmentData Environment;
    private RHolder Holder;

    public EnvironmentResult() {
    }

    public int getEnvVersionNumber() {
        return EnvVersionNumber;
    }

    public void setEnvVersionNumber(int envVersionNumber) {
        EnvVersionNumber = envVersionNumber;
    }

    public REnvironmentData getEnvironment() {
        return Environment;
    }

    public void setEnvironment(REnvironmentData environment) {
        Environment = environment;
    }

    public RHolder getHolder() {
        return Holder;
    }

    public void setHolder(RHolder holder) {
        Holder = holder;
    }
}
