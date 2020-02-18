package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class ValuesApiResponse implements Serializable {

    private Version version;
    private ContentEnvironment contentEnvironment;
    private ContractData contractData;

    public ValuesApiResponse() {
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public ContentEnvironment getContentEnvironment() {
        return contentEnvironment;
    }

    public void setContentEnvironment(ContentEnvironment contentEnvironment) {
        this.contentEnvironment = contentEnvironment;
    }

    public ContractData getContractData() {
        return contractData;
    }

    public void setContractData(ContractData contractData) {
        this.contractData = contractData;
    }

}
