package edu.kmaooad.capstone23.competences.events;

import edu.kmaooad.capstone23.competences.dal.Topic;

public class TopicDeleted {
    private Topic topic;

    public TopicDeleted(Topic topic) {
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }
}
