package com.conduent.hcesdk.entities.result;

public class REvent {
    private String EventCode;
    private int EventContractPointer;
    private int EventDevice;
    private int EventLocationID;
    private int EventRouteNumber;
    private int EventServiceProvider;

    public REvent() {
    }

    public String getEventCode() {
        return EventCode;
    }

    public void setEventCode(String eventCode) {
        EventCode = eventCode;
    }

    public int getEventContractPointer() {
        return EventContractPointer;
    }

    public void setEventContractPointer(int eventContractPointer) {
        EventContractPointer = eventContractPointer;
    }

    public int getEventDevice() {
        return EventDevice;
    }

    public void setEventDevice(int eventDevice) {
        EventDevice = eventDevice;
    }

    public int getEventLocationID() {
        return EventLocationID;
    }

    public void setEventLocationID(int eventLocationID) {
        EventLocationID = eventLocationID;
    }

    public int getEventRouteNumber() {
        return EventRouteNumber;
    }

    public void setEventRouteNumber(int eventRouteNumber) {
        EventRouteNumber = eventRouteNumber;
    }

    public int getEventServiceProvider() {
        return EventServiceProvider;
    }

    public void setEventServiceProvider(int eventServiceProvider) {
        EventServiceProvider = eventServiceProvider;
    }
}
