package edu.kmaooad.capstone23.ban.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import edu.kmaooad.capstone23.removeAll.commands.RemoveAll;
import edu.kmaooad.capstone23.removeAll.handlers.AllRemoved;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

@QuarkusTest
public class BanEntityHandlerTests {

    @Inject
    CommandHandler<BanEntity, EntityBanned> banEntityHandler;

    @Inject
    CommandHandler<CreateOrg, OrgCreated> createOrgHandler;

    @Inject
    CommandHandler<CreateDepartment, DepartmentCreated> createDepartmentHandler;

    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> createMemberHandler;


    @Inject
    CommandHandler<RemoveAll, AllRemoved> removeAllHandler;
    @BeforeEach
    void testInit() {
        removeAllHandler.handle(new RemoveAll());
    }

    @Test
    @DisplayName("Ban Org Handler: Basic Test")
    void testSuccessfulHandlingForOrg() {
        testSuccessfulHandlingForEntity(BannedEntityType.Organization);
    }

    @Test
    @DisplayName("Ban Department Handler: Basic Test")
    void testSuccessfulHandlingForDepartment() {
        testSuccessfulHandlingForEntity(BannedEntityType.Department);
    }

    @Test
    @DisplayName("Ban Member Handler: Basic Test")
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
    }

    @Test
    @DisplayName("Ban Org Handler: Org doesn't exist test")
    void testUnsuccessfulHandlingForOrg() {
        testUnsuccessfulHandlingForEntity(BannedEntityType.Organization);
    }

    @Test
    @DisplayName("Ban Department Handler: Department doesn't exist test")
    void testUnsuccessfulHandlingForDepartment() {
        testUnsuccessfulHandlingForEntity(BannedEntityType.Department);
    }


    @Test
    @DisplayName("Ban Department Handler: Member doesn't exist test")
    void testUnsuccessfulHandlingForMember() {
        testUnsuccessfulHandlingForEntity(BannedEntityType.Member);
    }

    @Test
    @DisplayName("Ban Illegal Entity Handler: Entity can't be banned")
    void testIllegalEntity() {
        var illegalEntity = "illegal";

        var command = new BanEntity();
        command.setEntityType(illegalEntity);
        command.setEntityId(new ObjectId());
        command.setReason("Hello there");

        var result = banEntityHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNotNull(result.toError());
    }

    void testUnsuccessfulHandlingForEntity(BannedEntityType entityType) {
        var command = new BanEntity();
        command.setEntityType(entityType.toString());
        command.setEntityId(new ObjectId());
        command.setReason("Hello there");

        var result = banEntityHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNotNull(result.toError());
    }

    String createEntity(BannedEntityType entityType) {
        return switch (entityType) {
            case Organization -> createOrg();
            case Department -> {
                createOrg();
                yield createDepartment("NaUKMA");
            }
            case Member -> {
                var orgId = createOrg();
                yield createMember(new ObjectId(orgId));
            }
        };
    }

    String createMember(ObjectId orgId) {
        var command = new CreateBasicMember();
        command.setOrgId(orgId);
        command.setFirstName("John");
        command.setLastName("Doe");
        command.setEmail(randomEmail());

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

        System.out.println(result.getMessage());
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getOrgId().isEmpty());
        return result.getValue().getOrgId();
    }

    private String randomEmail() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        return generatedString + "@mail.com";
    }

}
