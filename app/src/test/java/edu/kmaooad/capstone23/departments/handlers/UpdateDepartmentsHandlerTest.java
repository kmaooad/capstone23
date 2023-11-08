package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.handlers.BanEntityHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.UpdateDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import edu.kmaooad.capstone23.departments.events.DepartmentUpdated;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.drivers.OrgDriver;
import edu.kmaooad.capstone23.orgs.handlers.CreateOrgHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

@QuarkusTest
public class UpdateDepartmentsHandlerTest {
    @Inject
    UpdateDepartmentHandler handler;

    @Inject
    DepartmentService departmentService;

    @Inject
    DepartmentDriver departmentDriver;

    private String idToUpdate;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    BanEntityHandler banEntityHandler;

    @Inject
    OrgDriver orgDriver;

    private void createParentOrg() {
          orgDriver.createOrg();
    }

    @BeforeEach
    void setUp() {
        createParentOrg();
        Department department = departmentDriver.createDepartment();

        idToUpdate = department.id.toString();
    }

    @AfterEach
    public void tearDown() {
        orgsRepository.deleteAll();
    }

    @Test
    @DisplayName("Update Departments: Basic successful handling when id and parent is set correctly")
    void testSuccessfulHandling() {
        String parentOrgName = "Org to Delete";
        String departmentName = "FSNST";


        UpdateDepartment command = new UpdateDepartment();
        command.setName(departmentName);
        command.setParent(parentOrgName);
        command.setId(idToUpdate);

        Result<DepartmentUpdated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        Department createdDepartment = departmentService.getDepartmentById(departmentName);
        Assertions.assertNotNull(createdDepartment);
        Assertions.assertEquals(departmentName, createdDepartment.name);
    }

    @Test
    @DisplayName("Update Departments: unsuccessful handling when the department is banned")
    void testUnsuccessfulHandlingWhenDepartmentIsBanned() {
        String parentOrgName = "Org to Delete";
        String departmentName = "FSNST";

        Department department = departmentService.createDepartment(departmentName, "Initial Department Description", parentOrgName);
        department.parent = parentOrgName;
        departmentService.updateDepartment(department);

        BanEntity banCommand = new BanEntity();
        banCommand.setEntityId(department.id);
        banCommand.setEntityType("Department");
        banCommand.setReason("Hello there");

        var banResult = banEntityHandler.handle(banCommand);
        Assertions.assertTrue(banResult.isSuccess());

        UpdateDepartment command = new UpdateDepartment();
        command.setName(departmentName);
        command.setParent(parentOrgName);
        command.setId(department.id.toString());

        Result<DepartmentUpdated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }
}
