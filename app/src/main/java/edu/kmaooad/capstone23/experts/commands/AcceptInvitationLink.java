package edu.kmaooad.capstone23.experts.commands;

import org.bson.types.ObjectId;

public class AcceptInvitationLink {

    ObjectId invitationId;

    public AcceptInvitationLink(ObjectId invitationId) {
        this.invitationId = invitationId;
    }

    public ObjectId getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(ObjectId invitationId) {
        this.invitationId = invitationId;
    }
}
