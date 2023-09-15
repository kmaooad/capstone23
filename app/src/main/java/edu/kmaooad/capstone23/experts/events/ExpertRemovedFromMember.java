package edu.kmaooad.capstone23.experts.events;

public class ExpertRemovedFromMember {
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public ExpertRemovedFromMember(String memberId) {
        this.memberId = memberId;
    }
}
