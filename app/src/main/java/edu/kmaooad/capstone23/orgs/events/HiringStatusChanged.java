package edu.kmaooad.capstone23.orgs.events;

import edu.kmaooad.capstone23.orgs.dal.HiringStatus;

public class HiringStatusChanged {

    private HiringStatus hiringStatus;
    private String orgId;

    public HiringStatus getHiringStatus() {
        return hiringStatus;
    }

    public String getOrgId() {
        return orgId;
    }

    public HiringStatusChanged(HiringStatus hiringStatus, String orgId) {
        this.hiringStatus = hiringStatus;
        this.orgId = orgId;
    }


}
