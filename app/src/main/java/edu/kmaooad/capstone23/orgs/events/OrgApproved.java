package edu.kmaooad.capstone23.orgs.events;

public class OrgApproved {
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public OrgApproved(String orgId) {
        this.orgId = orgId;
    }
}