package edu.kmaooad.capstone23.experts.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateExpert {
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String expertName;
    private String orgName;

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
