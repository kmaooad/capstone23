package edu.kmaooad.capstone23.experts.events;

public class ExpertAssigned {
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public ExpertAssigned(String memberId) {
        this.memberId = memberId;
    }
}