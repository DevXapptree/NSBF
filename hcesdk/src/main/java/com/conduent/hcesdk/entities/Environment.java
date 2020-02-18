package com.conduent.hcesdk.entities;

public class Environment {

    private String SFI;
    private Rule EnvApplicationVersionNumber;
    private Rule EnvBitmap;
    private Rule EnvNetworkId;
    private Rule EnvApplicationIssuerId;
    private Rule EnvApplicationEndDate;
    private Rule EnvAuthenticator;
    private Rule EnvDataBitmap;
    private Rule EnvDataCardStatus;


    public Environment() {

    }

    public String getSFI() {
        return SFI;
    }

    public void setSFI(String SFI) {
        this.SFI = SFI;
    }

    public Rule getEnvApplicationVersionNumber() {
        return EnvApplicationVersionNumber;
    }

    public void setEnvApplicationVersionNumber(Rule envApplicationVersionNumber) {
        EnvApplicationVersionNumber = envApplicationVersionNumber;
    }

    public Rule getEnvBitmap() {
        return EnvBitmap;
    }

    public void setEnvBitmap(Rule envBitmap) {
        EnvBitmap = envBitmap;
    }

    public Rule getEnvNetworkId() {
        return EnvNetworkId;
    }

    public void setEnvNetworkId(Rule envNetworkId) {
        EnvNetworkId = envNetworkId;
    }

    public Rule getEnvApplicationIssuerId() {
        return EnvApplicationIssuerId;
    }

    public void setEnvApplicationIssuerId(Rule envApplicationIssuerId) {
        EnvApplicationIssuerId = envApplicationIssuerId;
    }

    public Rule getEnvApplicationEndDate() {
        return EnvApplicationEndDate;
    }

    public void setEnvApplicationEndDate(Rule envApplicationEndDate) {
        EnvApplicationEndDate = envApplicationEndDate;
    }

    public Rule getEnvAuthenticator() {
        return EnvAuthenticator;
    }

    public void setEnvAuthenticator(Rule envAuthenticator) {
        EnvAuthenticator = envAuthenticator;
    }

    public Rule getEnvDataBitmap() {
        return EnvDataBitmap;
    }

    public void setEnvDataBitmap(Rule envDataBitmap) {
        EnvDataBitmap = envDataBitmap;
    }

    public Rule getEnvDataCardStatus() {
        return EnvDataCardStatus;
    }

    public void setEnvDataCardStatus(Rule envDataCardStatus) {
        EnvDataCardStatus = envDataCardStatus;
    }

}
