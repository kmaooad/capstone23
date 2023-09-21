package edu.kmaooad.capstone23.activities.events;

public class ExtraActivityCreated {

    private String cvId;

    public ExtraActivityCreated(String cvId) {
        this.cvId = cvId;
    }

    public String getCVId(){
        return cvId;
    }

}