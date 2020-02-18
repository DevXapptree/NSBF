package com.conduent.hcesdk;

public class ReadParameters {
    private SourceType sourceType;
    private String data;

    private ReadParameters(){

    }

    public ReadParameters(final SourceType sourceType, final String data){
        this.sourceType = sourceType;
        this.data = data;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
