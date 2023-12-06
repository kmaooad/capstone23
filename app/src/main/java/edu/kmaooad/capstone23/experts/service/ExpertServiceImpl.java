package edu.kmaooad.capstone23.experts.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.UriBuilder;
import org.bson.types.ObjectId;

@ApplicationScoped
public class ExpertServiceImpl implements ExpertService {

    public static final String ACCEPT_INVITATION_ENDPOINT = "/experts/invitation/accept";

    @Override
    public String createInvitationLink(ObjectId invitationId) {
        String host = System.getProperty("quarkus.http.host");
        var invitationLink = UriBuilder.fromUri(host);
        invitationLink.path(ACCEPT_INVITATION_ENDPOINT);
        invitationLink.queryParam("id", invitationId.toString());
        return invitationLink.build().toString();
    }

}
