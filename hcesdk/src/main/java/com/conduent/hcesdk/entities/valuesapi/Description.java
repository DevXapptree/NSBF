package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class Description implements Serializable {
    private DescriptionLanguage fr;
    private DescriptionLanguage en;
    private DescriptionLanguage de;
    private DescriptionLanguage es;

    public Description() {
    }

    public DescriptionLanguage getFr() {
        return fr;
    }

    public void setFr(DescriptionLanguage fr) {
        this.fr = fr;
    }

    public DescriptionLanguage getEn() {
        return en;
    }

    public void setEn(DescriptionLanguage en) {
        this.en = en;
    }

    public DescriptionLanguage getDe() {
        return de;
    }

    public void setDe(DescriptionLanguage de) {
        this.de = de;
    }

    public DescriptionLanguage getEs() {
        return es;
    }

    public void setEs(DescriptionLanguage es) {
        this.es = es;
    }

}
