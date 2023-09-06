package edu.kmaooad.capstone23.competences.events;

public class TopicUpdated {
    private final String topicId;

    public String getTopicId() {
        return topicId;
    }

    public TopicUpdated(String topicId) {
        this.topicId = topicId;
    }
}
