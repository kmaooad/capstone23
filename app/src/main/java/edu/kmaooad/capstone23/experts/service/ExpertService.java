package edu.kmaooad.capstone23.experts.service;

import edu.kmaooad.capstone23.experts.dal.Expert;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.UriBuilder;
import org.bson.types.ObjectId;

public interface ExpertService {

    String ACCEPT_INVITATION_ENDPOINT = "/experts/invitation/accept";

    String createInvitationLink(ObjectId invitationId);

    Expert findById(ObjectId id);
    Expert insert(Expert expert);
    void deleteExpert(Expert expert);
    Expert modify(Expert expert) throws IllegalArgumentException;
    Expert findByMemberId(ObjectId memberId);
}
