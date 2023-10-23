package edu.kmaooad.capstone23.feed_back.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateFeedBack {

    @NotBlank
    @Size(min = 5, max = 20)
    private String topic;

    @NotBlank
    @Size(min = 5, max = 1000)
    private String text;
    @NotNull
    private String feedBackId;

    public String getTopic() {
        return topic;
    }

    public String getText() {
        return text;
    }

    public String getFeedBackId() {
        return feedBackId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFeedBackId(String feedBackId) {
        this.feedBackId = feedBackId;
    }
}
