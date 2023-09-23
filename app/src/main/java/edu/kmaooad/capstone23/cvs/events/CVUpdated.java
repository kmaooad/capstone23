package edu.kmaooad.capstone23.cvs.events;

public class CVUpdated {
    private String cvId;

    public String getCVId(){
        return cvId;
    }

    public CVUpdated(String cvId) {
        this.cvId = cvId;
    }
}
