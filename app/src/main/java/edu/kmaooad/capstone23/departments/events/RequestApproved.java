package edu.kmaooad.capstone23.departments.events;

public class RequestApproved {
    private String id;
    public String getId() {
        return id;
    }

    public RequestApproved(String id) {
        this.id = id;
    }
}
