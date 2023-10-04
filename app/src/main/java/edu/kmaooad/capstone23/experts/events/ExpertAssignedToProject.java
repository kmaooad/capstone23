package edu.kmaooad.capstone23.experts.events;

public class ExpertAssignedToProject {
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public ExpertAssignedToProject(String memberId) {
        this.memberId = memberId;
    }
}