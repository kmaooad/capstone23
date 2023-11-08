package edu.kmaooad.capstone23.members.commands;

import jakarta.validation.constraints.NotNull;

public class DeleteMember {

    @NotNull
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public DeleteMember(String memberId) {
        this.memberId = memberId;
    }

    public DeleteMember() {
    }
}
