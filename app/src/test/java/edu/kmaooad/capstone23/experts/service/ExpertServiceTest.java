package edu.kmaooad.capstone23.experts.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ExpertServiceTest {

    private final String HOST = "http://localhost:8080";
    @Inject
    ExpertService expertService;

    @Test
    public void testInvitationLinkCreation() {
        System.setProperty("quarkus.http.host", HOST);
        var objectId = new ObjectId();
        String invitationLink = expertService.createInvitationLink(objectId);
        var expectedLink = HOST + ExpertService.ACCEPT_INVITATION_ENDPOINT + "?id=" + objectId.toString();
        Assertions.assertEquals(expectedLink, invitationLink);
    }
}
