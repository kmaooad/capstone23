package edu.kmaooad.capstone23.activities.events;

public class ExtracurricularActivityCreated {
    private String extracurricularActivityId;

    public String getExtracurricularActivityId() {
        return extracurricularActivityId;
    }
    
    public ExtracurricularActivityCreated(String extracurricularActivityId) {
        this.extracurricularActivityId = extracurricularActivityId;
    }
}
