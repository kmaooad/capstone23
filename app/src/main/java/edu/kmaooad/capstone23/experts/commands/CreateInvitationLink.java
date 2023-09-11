package edu.kmaooad.capstone23.experts.commands;

import edu.kmaooad.capstone23.experts.ExpertType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateInvitationLink {
    @NotBlank
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotBlank
    private ExpertType expertType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ExpertType getExpertType() {
        return expertType;
    }

    public void setExpertType(ExpertType expertType) {
        this.expertType = expertType;
    }
}
