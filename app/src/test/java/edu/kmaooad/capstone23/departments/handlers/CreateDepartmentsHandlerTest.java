package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.handlers.CreateOrgHandler;
import jakarta.inject.Inject;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

@QuarkusTest
public class CreateDepartmentsHandlerTest {
    @BeforeAll
    static void deleteAllData() {
        DepartmentsRepository repository = new DepartmentsRepository();
        repository.deleteAll();
    }
    @Inject
    CreateDepartmentHandler handler;

    @Inject
    CreateOrgHandler orgHandler;

    @Inject
    DepartmentsRepository departmentsRepository;

    @Inject
    OrgsRepository orgsRepository;

    @BeforeEach
    void setUp() {
        createParentOrg();
    }

    @AfterEach
    public void tearDown() {
        orgsRepository.deleteAll();
    }


    private void createParentOrg() {
        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName("NaUKMA");
        orgCommand.industry = "Education";
        orgCommand.website = "https://www.ukma.edu.ua/eng/";
        orgHandler.handle(orgCommand);
    }

    @Test
    @DisplayName("Create Departments: Basic successful handling when parent is set correctly")
    void testSuccessfulHandling() {
        String parentOrgName = "NaUKMA";
        String departmentName = "FSNST";

        CreateDepartment command = new CreateDepartment();
        command.setName(departmentName);
        command.setParent(parentOrgName);

        Result<DepartmentCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        Department createdDepartment = departmentsRepository.findByName(departmentName);
        Assertions.assertNotNull(createdDepartment);
        Assertions.assertEquals(departmentName, createdDepartment.name);
    }

    @Test
    @DisplayName("Create Departments: Basic error handling when parent is set incorrectly")
    void testCreateWithNonExistentParent() {
        String parentOrgName = "NonExistentParent";
        String departmentName = "NewDepartment";

        CreateDepartment command = new CreateDepartment();
        command.setName(parentOrgName);
        command.setParent(departmentName);

        Result<DepartmentCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.EXCEPTION, result.getErrorCode());
        Assertions.assertEquals("Parent not found", result.getMessage());
    }
}
