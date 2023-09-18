package edu.kmaooad.capstone23.experts.commands;

import edu.kmaooad.capstone23.experts.ExpertType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateInvitationLink {
    @NotBlank
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotNull
    private ExpertType expertType;

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String expertName;
    private String orgName;

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

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
