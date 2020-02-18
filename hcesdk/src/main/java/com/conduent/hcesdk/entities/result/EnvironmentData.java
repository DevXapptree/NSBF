package com.conduent.hcesdk.entities.result;

public class EnvironmentData {
    private int EnvApplicationIssuerID;
    private String EnvApplicationValidityEndDate;
    private int EnvAuthenticator;
    private int EnvNetworkID;

    public EnvironmentData() {
    }

    public int getEnvApplicationIssuerID() {
        return EnvApplicationIssuerID;
    }

    public void setEnvApplicationIssuerID(int envApplicationIssuerID) {
        EnvApplicationIssuerID = envApplicationIssuerID;
    }

    public String getEnvApplicationValidityEndDate() {
        return EnvApplicationValidityEndDate;
    }

    public void setEnvApplicationValidityEndDate(String envApplicationValidityEndDate) {
        EnvApplicationValidityEndDate = envApplicationValidityEndDate;
    }

    public int getEnvAuthenticator() {
        return EnvAuthenticator;
    }

    public void setEnvAuthenticator(int envAuthenticator) {
        EnvAuthenticator = envAuthenticator;
    }

    public int getEnvNetworkID() {
        return EnvNetworkID;
    }

    public void setEnvNetworkID(int envNetworkID) {
        EnvNetworkID = envNetworkID;
    }
}
