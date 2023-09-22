package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.experts.ExpertType;
import edu.kmaooad.capstone23.experts.commands.CreateInvitationLink;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

@QuarkusTest
public class CreateInvitationLinkHandlerTest {
    private static final String TO = "foo@quarkus.io";
    private static final String HOST = "localhost:8081";
    @Inject
    MockMailbox mailbox;
    @Inject
    OrgsRepository orgsRepository;

    private static final String ORG_NAME = "My org";

    @BeforeEach
    public void beforeEach() {
        Org org = new Org();
        org.name = ORG_NAME;
        orgsRepository.persist(org);
    }

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
        command.setExpertName("Expert Name");
        command.setOrgName(ORG_NAME);
        invitationLinkHandler.handle(command);
        Assertions.assertEquals(1, mailbox.getMailsSentTo(TO).size());
    }

    @AfterEach
    public void teardown() {
        orgsRepository.deleteAll();
    }
}
