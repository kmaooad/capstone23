package edu.kmaooad.capstone23.experts.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.UriBuilder;
import org.bson.types.ObjectId;

@RequestScoped
public class ExpertService {

    private static final String ACCEPT_INVITATION_ENDPOINT = "/experts/invitation/accept";

    public String createInvitationLink(ObjectId invitationId) {
        String host = System.getProperty("quarkus.http.host");
        var invitationLink = UriBuilder.fromUri(host);
        invitationLink.path(ACCEPT_INVITATION_ENDPOINT);
        invitationLink.queryParam("id", invitationId);
        return invitationLink.build().toString();
    }

}
