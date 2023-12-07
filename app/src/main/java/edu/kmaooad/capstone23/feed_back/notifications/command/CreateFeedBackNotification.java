package edu.kmaooad.capstone23.feed_back.notifications.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class CreateFeedBackNotification {

    @NotNull
    @PastOrPresent
    private LocalDateTime dateTimeCreated;
    @NotEmpty
    private String feedBackId;

    @NotEmpty
    @Pattern(regexp = "created_feed_back|deleted_feed_back")
    private String type;
    @NotEmpty
    @Pattern(regexp = "mail|sms|telegram")
    private String to;

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

    public LocalDateTime getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }
}
