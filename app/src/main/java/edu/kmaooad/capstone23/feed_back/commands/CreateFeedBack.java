package edu.kmaooad.capstone23.feed_back.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;


public class CreateFeedBack {

    @NotBlank
    @Size(min = 5, max = 20)
    private String topic;

    @NotBlank
    @Size(min = 5, max = 1000)
    private String text;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
