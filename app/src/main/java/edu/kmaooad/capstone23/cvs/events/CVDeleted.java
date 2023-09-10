package edu.kmaooad.capstone23.cvs.events;

import edu.kmaooad.capstone23.cvs.dal.CV;

public class CVDeleted {
    private String cvId;

    public String getCVId(){
        return cvId;
    }

    public CVDeleted(String cvId) {
        this.cvId = cvId;
    }
}
