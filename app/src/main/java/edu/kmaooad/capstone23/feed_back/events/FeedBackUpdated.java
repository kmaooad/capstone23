package edu.kmaooad.capstone23.feed_back.events;

public class FeedBackUpdated {
    private final String feedBackId;
    private final String feedBackTopic;
    private final String feedBackText;

    public FeedBackUpdated(String feedBackId, String topic, String text) {
        this.feedBackId = feedBackId;
        this.feedBackTopic = topic;
        this.feedBackText = text;
    }

    public String getFeedBackId() {
        return feedBackId;
    }

    public String getFeedBackTopic() {
        return feedBackTopic;

    }

    public String getFeedBackText() {
        return feedBackText;
    }

}
