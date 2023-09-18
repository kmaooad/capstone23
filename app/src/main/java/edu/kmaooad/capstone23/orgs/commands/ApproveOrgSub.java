package edu.kmaooad.capstone23.orgs.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ApproveOrgSub {
    @NotBlank
    @Size(min = 2, max = 50)
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
