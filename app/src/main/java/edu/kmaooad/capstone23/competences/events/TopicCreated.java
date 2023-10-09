package edu.kmaooad.capstone23.competences.events;

public class TopicCreated {
    private final String id;

    public String getId() {
        return id;
    }

    public TopicCreated(String id) {
        this.id = id;
    }
}
