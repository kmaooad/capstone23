package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.commands.DeleteExpert;
import edu.kmaooad.capstone23.experts.dal.ExpertNotificationRepository;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.dal.NotificationTriggerType;
import edu.kmaooad.capstone23.experts.dal.NotificationType;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.experts.events.ExpertDeleted;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteExpertHandlerTest {

    @Inject
    CommandHandler<DeleteExpert, ExpertDeleted> deleteHandler;
    @Inject
    CommandHandler<CreateExpert, ExpertCreated> createHandler;
    @Inject
    CommandHandler<CreateOrg, OrgCreated> orgHandler;
    @Inject
    ExpertsRepository expertsRepository;

    @Inject
    ExpertNotificationRepository repository;

    @Test
    public void testSuccessfulHandling() {
        String expertId = createTestExpert();

        DeleteExpert command = new DeleteExpert();
        command.setId(expertsRepository.findByName("Arkh Step").id);

        Result<ExpertDeleted> result = deleteHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertEquals(result.getValue().getExpertId(), expertId);
    }

    @Test
    public void testSuccessfulNotificationOnDelete() {
        createTestExpert();

        DeleteExpert command = new DeleteExpert();
        command.setId(expertsRepository.findByName("Arkh Step").id);

        Result<ExpertDeleted> result = deleteHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(repository.streamAll().anyMatch(expertNotification -> expertNotification.notificationTriggerType.equals(NotificationTriggerType.EXPERT_DELETED) && expertNotification.notificationType.equals(NotificationType.sms)));
    }


    @Test
    public void testInvalidInput() {
        DeleteExpert command = new DeleteExpert();
        command.setId(new ObjectId("64fe000000000a0000000000"));

        Result<ExpertDeleted> result = deleteHandler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), ErrorCode.NOT_FOUND);
    }

    private String createTestExpert() {
        String orgName = "Super Duper Delete Team";

        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName(orgName);
        orgCommand.industry = "Education";
        orgCommand.website = "https://www.ukma.edu.ua/eng/";
        orgHandler.handle(orgCommand);

        CreateExpert command = new CreateExpert();
        command.setExpertName("Arkh Step");
        command.setOrgName(orgName);

        Result<ExpertCreated> result = createHandler.handle(command);

        return result.getValue().getExpertId();
    }
}
