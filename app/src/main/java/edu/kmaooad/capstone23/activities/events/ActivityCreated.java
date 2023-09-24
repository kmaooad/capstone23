package edu.kmaooad.capstone23.activities.events;

public class ActivityCreated {
    private final String id;

    public ActivityCreated(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
