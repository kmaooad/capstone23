package edu.kmaooad.capstone23.competences.events;

import org.bson.types.ObjectId;

public class TopicCreated {

    private ObjectId topic;

    public TopicCreated(ObjectId topic) {
        this.topic = topic;
    }

    public ObjectId getTopic() {
        return topic;
    }
}
