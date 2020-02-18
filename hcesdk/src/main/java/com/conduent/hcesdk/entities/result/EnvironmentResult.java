package com.conduent.hcesdk.entities.result;

public class EnvironmentResult {
    private int EnvVersionNumber;
    private EnvironmentData Environment;
    private Holder Holder;

    public EnvironmentResult() {
    }

    public int getEnvVersionNumber() {
        return EnvVersionNumber;
    }

    public void setEnvVersionNumber(int envVersionNumber) {
        EnvVersionNumber = envVersionNumber;
    }

    public EnvironmentData getEnvironment() {
        return Environment;
    }

    public void setEnvironment(EnvironmentData environment) {
        Environment = environment;
    }

    public com.conduent.hcesdk.entities.result.Holder getHolder() {
        return Holder;
    }

    public void setHolder(com.conduent.hcesdk.entities.result.Holder holder) {
        Holder = holder;
    }
}
