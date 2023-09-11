package edu.kmaooad.capstone23.experts.events;

import edu.kmaooad.capstone23.orgs.dal.Org;

public class ExpertCreated {
    private String expertId;
    private Org org;

    public String getExpertId() {
        return expertId;
    }

    public Org getOrg() {
        return org;
    }

    public ExpertCreated(String expertId, Org org) {
        this.expertId = expertId;
        this.org = org;
    }
}
