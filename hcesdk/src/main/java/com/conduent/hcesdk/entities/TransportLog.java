package com.conduent.hcesdk.entities;

public class TransportLog {
    private String SFI;
    private Rule EventDateStamp;
    private Rule EventTimeStamp;
    private Rule EventBitmap;
    private Rule EventCode;
    private Rule EventServiceProvider;
    private Rule EventNotokCounter;
    private Rule EventSerialNumber;
    private Rule EventLocationId;
    private Rule EventLocationGate;
    private Rule EventDevice;
    private Rule EventRouteNumber;
    private Rule EventJourneyRun;
    private Rule EventVehicleId;
    private Rule EventContractPointer;
    private Rule EventData;
    private Rule EventDataDateFirstStamp;
    private Rule EventDataTimeFirstStamp;

    public TransportLog() {
    }

    public String getSFI() {
        return SFI;
    }

    public void setSFI(String SFI) {
        this.SFI = SFI;
    }

    public Rule getEventDateStamp() {
        return EventDateStamp;
    }

    public void setEventDateStamp(Rule eventDateStamp) {
        EventDateStamp = eventDateStamp;
    }

    public Rule getEventTimeStamp() {
        return EventTimeStamp;
    }

    public void setEventTimeStamp(Rule eventTimeStamp) {
        EventTimeStamp = eventTimeStamp;
    }

    public Rule getEventBitmap() {
        return EventBitmap;
    }

    public void setEventBitmap(Rule eventBitmap) {
        EventBitmap = eventBitmap;
    }

    public Rule getEventCode() {
        return EventCode;
    }

    public void setEventCode(Rule eventCode) {
        EventCode = eventCode;
    }

    public Rule getEventServiceProvider() {
        return EventServiceProvider;
    }

    public void setEventServiceProvider(Rule eventServiceProvider) {
        EventServiceProvider = eventServiceProvider;
    }

    public Rule getEventNotokCounter() {
        return EventNotokCounter;
    }

    public void setEventNotokCounter(Rule eventNotokCounter) {
        EventNotokCounter = eventNotokCounter;
    }

    public Rule getEventSerialNumber() {
        return EventSerialNumber;
    }

    public void setEventSerialNumber(Rule eventSerialNumber) {
        EventSerialNumber = eventSerialNumber;
    }

    public Rule getEventLocationId() {
        return EventLocationId;
    }

    public void setEventLocationId(Rule eventLocationId) {
        EventLocationId = eventLocationId;
    }

    public Rule getEventLocationGate() {
        return EventLocationGate;
    }

    public void setEventLocationGate(Rule eventLocationGate) {
        EventLocationGate = eventLocationGate;
    }

    public Rule getEventDevice() {
        return EventDevice;
    }

    public void setEventDevice(Rule eventDevice) {
        EventDevice = eventDevice;
    }

    public Rule getEventRouteNumber() {
        return EventRouteNumber;
    }

    public void setEventRouteNumber(Rule eventRouteNumber) {
        EventRouteNumber = eventRouteNumber;
    }

    public Rule getEventJourneyRun() {
        return EventJourneyRun;
    }

    public void setEventJourneyRun(Rule eventJourneyRun) {
        EventJourneyRun = eventJourneyRun;
    }

    public Rule getEventVehicleId() {
        return EventVehicleId;
    }

    public void setEventVehicleId(Rule eventVehicleId) {
        EventVehicleId = eventVehicleId;
    }

    public Rule getEventContractPointer() {
        return EventContractPointer;
    }

    public void setEventContractPointer(Rule eventContractPointer) {
        EventContractPointer = eventContractPointer;
    }

    public Rule getEventData() {
        return EventData;
    }

    public void setEventData(Rule eventData) {
        EventData = eventData;
    }

    public Rule getEventDataDateFirstStamp() {
        return EventDataDateFirstStamp;
    }

    public void setEventDataDateFirstStamp(Rule eventDataDateFirstStamp) {
        EventDataDateFirstStamp = eventDataDateFirstStamp;
    }

    public Rule getEventDataTimeFirstStamp() {
        return EventDataTimeFirstStamp;
    }

    public void setEventDataTimeFirstStamp(Rule eventDataTimeFirstStamp) {
        EventDataTimeFirstStamp = eventDataTimeFirstStamp;
    }
}
