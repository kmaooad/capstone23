package edu.kmaooad.capstone23.experts.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ExpertServiceTest {

    private static final String HOST = "localhost:8081";
    @Inject
    ExpertService expertService;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("quarkus.http.host", HOST);
    }

    @Test
    public void testInvitationLinkCreation() {
        var objectId = new ObjectId();
        String invitationLink = expertService.createInvitationLink(objectId);
        var expectedLink = HOST + "\"/experts/invitation/accept\"?id=" + objectId;
        Assertions.assertEquals(expectedLink, invitationLink);
    }
}
