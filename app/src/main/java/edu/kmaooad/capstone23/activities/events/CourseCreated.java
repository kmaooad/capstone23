package edu.kmaooad.capstone23.activities.events;

public class CourseCreated {
    private final String id;

    public CourseCreated(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
