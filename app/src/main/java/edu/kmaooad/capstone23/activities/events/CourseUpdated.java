package edu.kmaooad.capstone23.activities.events;

public class CourseUpdated {
    private final String id;
    private final String name;

    public CourseUpdated(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
