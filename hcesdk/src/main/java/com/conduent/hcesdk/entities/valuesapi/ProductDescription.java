package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class ProductDescription implements Serializable {
    private ProductDescriptionLanguage fr;
    private ProductDescriptionLanguage en;
    private ProductDescriptionLanguage de;
    private ProductDescriptionLanguage es;

    public ProductDescription() {
    }

    public ProductDescriptionLanguage getFr() {
        return fr;
    }

    public void setFr(ProductDescriptionLanguage fr) {
        this.fr = fr;
    }

    public ProductDescriptionLanguage getEn() {
        return en;
    }

    public void setEn(ProductDescriptionLanguage en) {
        this.en = en;
    }

    public ProductDescriptionLanguage getDe() {
        return de;
    }

    public void setDe(ProductDescriptionLanguage de) {
        this.de = de;
    }

    public ProductDescriptionLanguage getEs() {
        return es;
    }

    public void setEs(ProductDescriptionLanguage es) {
        this.es = es;
    }

}
