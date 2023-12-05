package edu.kmaooad.capstone23.feed_back.notifications;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class FeedBackNotification {

    @NotEmpty
    private String notId;

    @NotEmpty
    @Pattern(regexp = "created_feed_back|deleted_feed_back")
    private String type;
    @NotEmpty
    @Pattern(regexp = "mail|sms|telegram")
    private String from;

    public String getNotId() {
        return notId;
    }

    public void setNotId(String notId) {
        this.notId = notId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
