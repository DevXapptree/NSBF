package com.conduent.hcesdk;

public enum SFICodeType {
    SFI06("06"), SFI07("07"), SFI08("08"), SFI09("09"), SFI19("19"), SFI0a("0a"), SFI0b("0b"), SFI0c("0c"), SFI0d("0d"), SFI1a("1a"), SFI1b("1b"), SFI1c("1c"), SFI1d("1d"), SFI1e("1e");

    private String code;
    SFICodeType(String code) {
        this.code = code;
    }
    public String code() {
        return code;
    }
}
