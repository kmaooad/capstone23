package edu.kmaooad.capstone23.orgs.commands;

import edu.kmaooad.capstone23.orgs.dal.HiringStatus;
import org.bson.types.ObjectId;

public class SetHiringStatus {

    private HiringStatus hiringStatus;
    private String orgId;
    public String getOrgId(){
        return orgId;
    }
    public HiringStatus getHiringStatus() {
        return hiringStatus;
    }
    public void setHiringStatus(HiringStatus hiringStatus) {
        this.hiringStatus = hiringStatus;
    }
    public void setOrgID(String orgId) {
        this.orgId = orgId;
    }

}
