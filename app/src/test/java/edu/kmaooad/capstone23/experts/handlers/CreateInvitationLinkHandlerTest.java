package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.experts.ExpertType;
import edu.kmaooad.capstone23.experts.commands.CreateInvitationLink;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateInvitationLinkHandlerTest {
    private static final String TO = "foo@quarkus.io";
    private static final String HOST = "http://localhost:8080";
    @Inject
    MockMailbox mailbox;

    @Inject
    CreateInvitationLinkHandler invitationLinkHandler;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("quarkus.http.host", HOST);
    }
    @BeforeEach
    void init() {
        mailbox.clear();
    }

    @Test
    void testSendsEmailOnRequest() {
        var command = new CreateInvitationLink();
        command.setEmail(TO);
        command.setExpertType(ExpertType.INDUSTRY);
        invitationLinkHandler.handle(command);
        Assertions.assertEquals(1, mailbox.getMailsSentTo(TO).size());
    }

}
