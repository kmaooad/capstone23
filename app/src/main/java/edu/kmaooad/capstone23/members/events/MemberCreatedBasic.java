package edu.kmaooad.capstone23.members.events;

public class MemberCreatedBasic {
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public MemberCreatedBasic(String memberId) {
        this.memberId = memberId;
    }
}
