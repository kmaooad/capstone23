package edu.kmaooad.capstone23.activities.events;

public class ExtraActivityCreated {
    private final String id;

    public ExtraActivityCreated(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}