package edu.kmaooad.capstone23.experts.events;

public class ExpertCreated {
    private String expertId;

    public String getExpertId() {
        return expertId;
    }

    public ExpertCreated(String expertId) {
        this.expertId = expertId;
    }
}
