package edu.kmaooad.capstone23.experts.commands;

import edu.kmaooad.capstone23.experts.ExpertType;
import jakarta.validation.constraints.NotBlank;

public class CreateInvitationLink {

    @NotBlank
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
