package edu.kmaooad.capstone23.competences.events;

import edu.kmaooad.capstone23.competences.dal.Topic;

public class TopicUpdated {


    private Topic topic;

    public TopicUpdated(Topic topic) {
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }
}
