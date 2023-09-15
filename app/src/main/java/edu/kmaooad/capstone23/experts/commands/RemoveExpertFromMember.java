package edu.kmaooad.capstone23.experts.commands;

import org.bson.types.ObjectId;

public class RemoveExpertFromMember {
    private ObjectId memberId;
    private String orgName;

    public ObjectId getMemberId() {
        return memberId;
    }

    public void setMemberId(ObjectId memberId) {
        this.memberId = memberId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
