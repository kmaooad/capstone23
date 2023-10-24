package edu.kmaooad.capstone23.experts.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.UriBuilder;
import org.bson.types.ObjectId;

public interface ExpertService {

    String ACCEPT_INVITATION_ENDPOINT = "/experts/invitation/accept";

    String createInvitationLink(ObjectId invitationId);

}
