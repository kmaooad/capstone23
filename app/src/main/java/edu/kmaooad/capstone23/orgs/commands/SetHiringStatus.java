package edu.kmaooad.capstone23.orgs.commands;

import edu.kmaooad.capstone23.orgs.dal.HiringStatus;
import org.bson.types.ObjectId;

public class SetHiringStatus {

    private HiringStatus hiringStatus;
    private ObjectId orgID;
    public HiringStatus getHiringStatusName() {
        return hiringStatus;
    }
    public void setHiringStatus(HiringStatus hiringStatus) {
        this.hiringStatus = hiringStatus;
    }


}
