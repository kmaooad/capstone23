package edu.kmaooad.capstone23.experts.service;

import org.bson.types.ObjectId;

public interface ExpertService {

    String createInvitationLink(ObjectId invitationId);
}
