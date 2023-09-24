package edu.kmaooad.capstone23.members.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class GetMemberByEmail {
    @NotBlank
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
