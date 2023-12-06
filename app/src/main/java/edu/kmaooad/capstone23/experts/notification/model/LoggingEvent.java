package edu.kmaooad.capstone23.experts.notification.model;

import edu.kmaooad.capstone23.experts.notification.EventType;

public class LoggingEvent extends Event {


    private String loggingInfo;

    public LoggingEvent(EventType eventType, String loggingInfo) {
        super(eventType);
        this.loggingInfo = loggingInfo;
    }

    public String getLoggingInfo() {
        return loggingInfo;
    }
}
