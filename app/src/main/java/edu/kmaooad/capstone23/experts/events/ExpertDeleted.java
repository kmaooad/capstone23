package edu.kmaooad.capstone23.experts.events;

public class ExpertDeleted {
    private String expertId;

    public String getExpertId() {
        return expertId;
    }

    public ExpertDeleted(String expertId) {
        this.expertId = expertId;
    }
}
