package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;
import java.util.ArrayList;

public class ContractData implements Serializable {

    private ArrayList<Value> contractStatus;
    private ArrayList<Product> products;
    private ArrayList<Value> contractCustomerProfile;
    private ArrayList<Zone> contractValidityZones;

    public ContractData() {

    }

    public ArrayList<Value> getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ArrayList<Value> contractStatus) {
        this.contractStatus = contractStatus;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Value> getContractCustomerProfile() {
        return contractCustomerProfile;
    }

    public void setContractCustomerProfile(ArrayList<Value> contractCustomerProfile) {
        this.contractCustomerProfile = contractCustomerProfile;
    }

    public ArrayList<Zone> getContractValidityZones() {
        return contractValidityZones;
    }

    public void setContractValidityZones(ArrayList<Zone> contractValidityZones) {
        this.contractValidityZones = contractValidityZones;
    }

}
