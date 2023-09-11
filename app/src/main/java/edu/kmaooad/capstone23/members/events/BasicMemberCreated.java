package edu.kmaooad.capstone23.members.events;

public class BasicMemberCreated {
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public BasicMemberCreated(String memberId) {
        this.memberId = memberId;
    }
}
