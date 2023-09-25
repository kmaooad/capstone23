package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import edu.kmaooad.capstone23.experts.commands.AssignDepartmentToExpert;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.DepartmentAssignedToExpert;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AssignDepartmentToExpertHandlerTest {
    private static final String ORG_NAME = "Organisationn";
    private ObjectId expertId;
    private ObjectId departmentId;
    private ObjectId orgId;
    @Inject
    CommandHandler<CreateOrg, OrgCreated> orgHandler;
    @Inject
    CommandHandler<CreateDepartment, DepartmentCreated> departmentHandler;
    @Inject
    CommandHandler<CreateExpert, ExpertCreated> expertHandler;
    @Inject
    CommandHandler<AssignDepartmentToExpert, DepartmentAssignedToExpert> assignHandler;
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    DepartmentsRepository departmentsRepository;
    @Inject
    ExpertsRepository expertsRepository;

    @BeforeEach
    void setUp() {
        orgId = createTestOrg();
        departmentId = createTestDepartment();
        expertId = createTestExpert();
    }

    @Test
    @DisplayName("Assign Department To Expert: Basic")
    public void testSuccessfulHandling() {
        AssignDepartmentToExpert assignDepartmentToExpert = new AssignDepartmentToExpert();
        assignDepartmentToExpert.setExpertId(expertId.toHexString());
        assignDepartmentToExpert.setDepartmentId(departmentId.toHexString());

        Result<DepartmentAssignedToExpert> result = assignHandler.handle(assignDepartmentToExpert);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        List<String> idStrings = result.getValue().getExpert().departments.stream()
                .map(x -> x.id.toHexString())
                .toList();
        Assertions.assertTrue(idStrings.contains(
                departmentsRepository.findById(departmentId).id.toHexString()));
    }

    @Test
    @DisplayName("Assign Department To Expert: Non-Existent Expert")
    public void testHandlingWithInvalidExpert() {
        AssignDepartmentToExpert assignDepartmentToExpert = new AssignDepartmentToExpert();
        assignDepartmentToExpert.setExpertId("64fe000000000a0000000000");
        assignDepartmentToExpert.setDepartmentId(departmentId.toHexString());

        Result<DepartmentAssignedToExpert> result = assignHandler.handle(assignDepartmentToExpert);

        Assertions.assertEquals(result.getErrorCode(), ErrorCode.NOT_FOUND);
    }

    @Test
    @DisplayName("Assign Department To Expert: Non-Existent Department")
    public void testHandlingWithInvalidDepartment() {
        AssignDepartmentToExpert assignDepartmentToExpert = new AssignDepartmentToExpert();
        assignDepartmentToExpert.setExpertId(orgId.toHexString());
        assignDepartmentToExpert.setDepartmentId("64fe000000000a0000000000");

        Result<DepartmentAssignedToExpert> result = assignHandler.handle(assignDepartmentToExpert);

        Assertions.assertEquals(result.getErrorCode(), ErrorCode.NOT_FOUND);
    }

    @Test
    @DisplayName("Assign Department To Expert: Expert Is Already In The Wished Department")
    public void testHandlingWithAlreadyAssignedDepartment() {
        AssignDepartmentToExpert assignDepartmentToExpert = new AssignDepartmentToExpert();
        assignDepartmentToExpert.setExpertId(expertId.toHexString());
        assignDepartmentToExpert.setDepartmentId(departmentId.toHexString());

        assignHandler.handle(assignDepartmentToExpert);
        Result<DepartmentAssignedToExpert> result = assignHandler.handle(assignDepartmentToExpert);

        Assertions.assertEquals(ErrorCode.CONFLICT, result.getErrorCode());
    }

    @AfterEach
    void tearDown() {
        expertsRepository.deleteAll();
        departmentsRepository.deleteAll();
        orgsRepository.deleteAll();
    }

    private ObjectId createTestExpert() {
        CreateExpert command = new CreateExpert();
        command.setExpertName("Redyska Sosyska");
        command.setOrgName(ORG_NAME);
        Result<ExpertCreated> result = expertHandler.handle(command);
        return new ObjectId(result.getValue().getExpertId());
    }

    private ObjectId createTestDepartment() {
        CreateDepartment departmentCommand = new CreateDepartment();
        departmentCommand.setName("Some Department");
        departmentCommand.setDescription("Some Description");
        departmentCommand.setParent(ORG_NAME);
        Result<DepartmentCreated> result = departmentHandler.handle(departmentCommand);
        return new ObjectId(result.getValue().getId());
    }

    private ObjectId createTestOrg() {
        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName(ORG_NAME);
        orgCommand.industry = "Education";
        orgCommand.website = "https://www.ukma.edu.ua/eng/";
        orgCommand.description = "A very nice description";
        Result<OrgCreated> result = orgHandler.handle(orgCommand);
        return new ObjectId(result.getValue().getOrgId());
    }
}
