package edu.kmaooad.capstone23.experts.commands;

import org.bson.types.ObjectId;

public class AssignExpertToMember {
    private ObjectId memberId;

    public ObjectId getMemberId() {
        return memberId;
    }

    public void setMemberId(ObjectId memberId) {
        this.memberId = memberId;
    }
}