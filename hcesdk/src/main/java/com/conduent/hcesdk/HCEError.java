package com.conduent.hcesdk;

public class HCEError {

    private String errorMessage;
    private int errorCode;
    private Throwable throwable;

    public HCEError() {
    }

    public HCEError(String errorMessage, int errorCode, Throwable throwable) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.throwable = throwable;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
