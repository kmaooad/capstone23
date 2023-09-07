package edu.kmaooad.capstone23.members.events;

public class CreatedMemberBasic {
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public CreatedMemberBasic(String memberId) {
        this.memberId = memberId;
    }
}
