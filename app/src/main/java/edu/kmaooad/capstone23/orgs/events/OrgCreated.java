package edu.kmaooad.capstone23.orgs.events;

public class OrgCreated {
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public OrgCreated(String orgId) {
        this.orgId = orgId;
    }
}
