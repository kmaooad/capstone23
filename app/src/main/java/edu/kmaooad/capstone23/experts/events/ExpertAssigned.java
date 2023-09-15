package edu.kmaooad.capstone23.experts.events;

import edu.kmaooad.capstone23.orgs.dal.Org;

public class ExpertAssigned {
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public ExpertAssigned(String memberId) {
        this.memberId = memberId;
    }
}