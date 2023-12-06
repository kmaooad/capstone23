package edu.kmaooad.capstone23.orgs.commands;

import edu.kmaooad.capstone23.orgs.dal.HiringStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateOrg {

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String orgName;
    private HiringStatus hiringStatus;

    @NotBlank
    public String industry;

    @NotBlank
    public String website;

    public String description;

    public String emailDomain;

    public String getOrgName() {
        return orgName;
    }

    public HiringStatus getHiringStatus() { return hiringStatus; }

    public void setHiringStatus(HiringStatus hiringStatus) { this.hiringStatus = hiringStatus; }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}