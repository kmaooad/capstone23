package edu.kmaooad.capstone23.members.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DeleteMember {
    @NotBlank
    @Size(min = 24, max = 24)
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
