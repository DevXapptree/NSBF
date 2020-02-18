package com.conduent.hcesdk.room;

import android.arch.persistence.room.Embedded;

public class Description {
    @Embedded(prefix = "fr_")
    private DescriptionLanguage fr;

    @Embedded(prefix = "en_")
    private DescriptionLanguage en;

    @Embedded(prefix = "de_")
    private DescriptionLanguage de;

    @Embedded(prefix = "es_")
    private DescriptionLanguage es;

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
