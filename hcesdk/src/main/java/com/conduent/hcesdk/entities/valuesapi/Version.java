package com.conduent.hcesdk.entities.valuesapi;

import java.io.Serializable;

public class Version implements Serializable {
    private String id;
    private String comment;

    public Version() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
