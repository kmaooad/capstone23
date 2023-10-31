package edu.kmaooad.capstone23.experts.events;

public class ExpertRemovedFromProject {
    private String expertId;

    public String getExpertId() {
        return expertId;
    }

    public ExpertRemovedFromProject(String expertId) {
        this.expertId = expertId;
    }
}
