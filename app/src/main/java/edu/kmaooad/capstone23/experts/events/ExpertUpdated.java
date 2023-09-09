package edu.kmaooad.capstone23.experts.events;

public class ExpertUpdated {
    private String expertId;

    public String getExpertId() {
        return expertId;
    }

    public ExpertUpdated(String expertId) {
        this.expertId = expertId;
    }
}
