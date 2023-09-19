package edu.kmaooad.capstone23.competences.events;

public class TopicDeleted {
    private final String id;

    public TopicDeleted(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

