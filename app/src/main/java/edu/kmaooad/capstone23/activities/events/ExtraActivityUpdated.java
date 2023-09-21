package edu.kmaooad.capstone23.activities.events;

public class ExtraActivityUpdated {

    private String cvId;

    public ExtraActivityUpdated(String cvId) {
        this.cvId = cvId;
    }

    public String getCVId(){
        return cvId;
    }

}