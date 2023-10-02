package edu.kmaooad.capstone23.orgs.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RelateJobToOrg {
    @NotBlank
    @Size(min = 2, max = 50)
    private String orgId;

    @NotBlank
    private String jobId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
