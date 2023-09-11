package edu.kmaooad.capstone23.orgs.commands;

import edu.kmaooad.capstone23.orgs.dal.HiringStatus;
import org.bson.types.ObjectId;

public class SetHiringStatus {

    private HiringStatus hiringStatus;
    private String orgID;
    public String getOrgID(){
        return orgID;
    }
    public HiringStatus getHiringStatus() {
        return hiringStatus;
    }
    public void setHiringStatus(HiringStatus hiringStatus) {
        this.hiringStatus = hiringStatus;
    }
    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

}
