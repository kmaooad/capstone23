package edu.kmaooad.capstone23.cvs.events;

public class CVCreated {

    private String cvId;

    public String getCVId(){
        return cvId;
    }

    public CVCreated(String cvId) {
        this.cvId = cvId;
    }
}