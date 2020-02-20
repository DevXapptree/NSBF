package com.conduent.hcesdk.entities.result;

public class TransportLogResult {
    private REvent Event;
    private String EventDate;
    private String EventTime;

    public TransportLogResult() {
    }

    public REvent getEvent() {
        return Event;
    }

    public void setEvent(REvent event) {
        Event = event;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }

    public String getEventTime() {
        return EventTime;
    }

    public void setEventTime(String eventTime) {
        EventTime = eventTime;
    }
}
