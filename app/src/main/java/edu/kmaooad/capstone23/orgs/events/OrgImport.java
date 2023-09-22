package edu.kmaooad.capstone23.orgs.events;

import java.util.List;

public class OrgImport {
    private List<String> orgIds;

    public List<String> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<String> orgIds) {
        this.orgIds = orgIds;
    }

    public OrgImport(List<String> orgIds) {
      this.orgIds = orgIds; 
    }
}
