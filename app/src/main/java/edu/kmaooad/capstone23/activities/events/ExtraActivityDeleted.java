package edu.kmaooad.capstone23.activities.events;

public class ExtraActivityDeleted {

    private String cvId;

    public ExtraActivityDeleted(String cvId) {
        this.cvId = cvId;
    }

    public String getCVId(){
        return cvId;
    }

}