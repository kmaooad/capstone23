package edu.kmaooad.capstone23.activities.events;

public class ExtracurricularActivityDeleted {
    private String id;

    public String getId() {
        return id;
    }

    public ExtracurricularActivityDeleted(String ExtracurricularActivityId) {
        this.id = ExtracurricularActivityId;
    }
}
