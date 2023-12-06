package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.experts.commands.AcceptInvitationLink;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitation;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitationRepository;
import edu.kmaooad.capstone23.experts.notification.EventDispatcher;
import edu.kmaooad.capstone23.experts.notification.listeners.TelegramListener;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AcceptInvitationLinkHandlerTest {

    @Inject
    AcceptInvitationLinkHandler acceptInvitationLinkHandler;
    @Inject
    ExpertInvitationRepository invitationRepository;
    @Inject
    OrgsRepository orgsRepository;
    @Test
    void testCreateExpertSuccessfully() {
        var invitation = new ExpertInvitation();
        invitation.expertName = "Expert name";
        invitation.email = "expert@mail.com";
        var org = new Org();
        org.name = "Test org";
        orgsRepository.persist(org);
        invitation.org = org;

        invitationRepository.persist(invitation);

        var acceptInvitationCommand = new AcceptInvitationLink(invitation.id);

        var result = acceptInvitationLinkHandler.handle(acceptInvitationCommand);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getExpertId().isEmpty());
    }

    @Test
    void testEventSend() {
        var invitation = new ExpertInvitation();
        invitation.expertName = "Expert name";
        invitation.email = "expert@mail.com";
        var org = new Org();
        org.name = "Test org";
        orgsRepository.persist(org);
        invitation.org = org;

        invitationRepository.persist(invitation);

        var acceptInvitationCommand = new AcceptInvitationLink(invitation.id);

        var result = acceptInvitationLinkHandler.handle(acceptInvitationCommand);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getExpertId().isEmpty());
    }

    @Test
    void testCreateExpertFailed() {
        var objectId = new ObjectId();
        var result = acceptInvitationLinkHandler.handle(new AcceptInvitationLink(objectId));

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.NOT_FOUND, result.getErrorCode());
        Assertions.assertEquals(String.format("Invitation id %s not found", objectId), result.getMessage());
    }

}
