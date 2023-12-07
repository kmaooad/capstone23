package edu.kmaooad.capstone23.feed_back.notifications.event;

import java.time.LocalDateTime;

public class FeedBackNotificationCreated {
    private String id;
    private String feedBackId;
    private String type;
    private String to;

    private LocalDateTime dateTimeCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(String feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public FeedBackNotificationCreated(String id, String feedBackId, String type, String to) {
        this.id = id;
        this.feedBackId = feedBackId;
        this.type = type;
        this.to = to;
    }

    public LocalDateTime getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

}
