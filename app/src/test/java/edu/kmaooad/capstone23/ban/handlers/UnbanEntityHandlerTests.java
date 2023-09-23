package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.commands.UnbanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.ban.events.EntityUnbanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UnbanEntityHandlerTests {

    @Inject
    CommandHandler<UnbanEntity, EntityUnbanned> unbanEntityHandler;

    @Inject
    CommandHandler<BanEntity, EntityBanned> banEntityHandler;

    @Inject
    CommandHandler<CreateOrg, OrgCreated> createOrgHandler;

    @Inject
    CommandHandler<CreateDepartment, DepartmentCreated> createDepartmentHandler;

    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> createMemberHandler;

    @Test
    @DisplayName("Unban Org Handler: Basic Test")
    void testSuccessfulHandlingForOrg() {
        testSuccessfulHandlingForEntity(BannedEntityType.Organization);
    }

    @Test
    @DisplayName("Unban Department Handler: Basic Test")
    void testSuccessfulHandlingForDepartment() {
        testSuccessfulHandlingForEntity(BannedEntityType.Department);
    }

    @Test
    @DisplayName("Unban Member Handler: Basic Test")
    void testSuccessfulHandlingForMember() {
        testSuccessfulHandlingForEntity(BannedEntityType.Member);
    }

    void testSuccessfulHandlingForEntity(BannedEntityType entityType) {
        var orgId = createEntity(entityType);

        var command = new BanEntity();
        command.setEntityType(entityType.toString());
        command.setEntityId(new ObjectId(orgId));
        command.setReason("Hello there");

        var result = banEntityHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        var unbanCommand = new UnbanEntity();
        unbanCommand.setEntityTypeName(entityType.toString());
        unbanCommand.setEntityId(new ObjectId(orgId));

        var unbanResult = unbanEntityHandler.handle(unbanCommand);
        Assertions.assertTrue(unbanResult.isSuccess());
        Assertions.assertNotNull(unbanResult.getValue().id());

        var secondUnbanResult = unbanEntityHandler.handle(unbanCommand);
        Assertions.assertTrue(secondUnbanResult.isSuccess());
        Assertions.assertNull(secondUnbanResult.getValue().id());
    }

    String createEntity(BannedEntityType entityType) {
        if (entityType == BannedEntityType.Organization) {
            return createOrg();
        } else if (entityType == BannedEntityType.Department) {
            createOrg();
            return createDepartment("NaUKMA");
        } else {
            var orgId = createOrg();
            return createMember(new ObjectId(orgId));
        }
    }

    String createMember(ObjectId orgId) {
        var command = new CreateBasicMember();
        command.setOrgId(orgId);
        command.setFirstName("John");
        command.setLastName("Doe");
        command.setEmail("john.doe@example.com");

        Result<BasicMemberCreated> result = createMemberHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getMemberId().isEmpty());

        return result.getValue().getMemberId();
    }

    String createDepartment(String orgName) {
        var command = new CreateDepartment();
        command.setParent(orgName);
        command.setName("IT");
        command.setDescription("the IT is a crawling hand from the Addams Family.");

        Result<DepartmentCreated> result = createDepartmentHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());

        return result.getValue().getId();
    }

    String createOrg() {
        var command = new CreateOrg();
        command.setOrgName("NaUKMA");
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";

        Result<OrgCreated> result = createOrgHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getOrgId().isEmpty());
        return result.getValue().getOrgId();
    }
}
