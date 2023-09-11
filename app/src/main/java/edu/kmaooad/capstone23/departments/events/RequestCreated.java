package edu.kmaooad.capstone23.departments.events;

public class RequestCreated {
    private String id;
    public String getId() {
        return id;
    }

    public RequestCreated(String requestID) {
        this.id = requestID;
    }
}
