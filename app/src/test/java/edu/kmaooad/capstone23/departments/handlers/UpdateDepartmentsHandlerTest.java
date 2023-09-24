package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.UpdateDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentUpdated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.handlers.CreateOrgHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

@QuarkusTest
public class UpdateDepartmentsHandlerTest {
    @Inject
    UpdateDepartmentHandler handler;

    @Inject
    DepartmentsRepository departmentsRepository;

    private String idToUpdate;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    CreateOrgHandler orgHandler;

    private void createParentOrg() {
        CreateOrg orgCommand = new CreateOrg();
        orgCommand.setOrgName("NaUKMA");
        orgCommand.industry = "Education";
        orgCommand.website = "https://www.ukma.edu.ua/eng/";
        orgHandler.handle(orgCommand);
    }

    @BeforeEach
    void setUp() {
        createParentOrg();
        Department department = new Department();

        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        departmentsRepository.insert(department);

        idToUpdate = department.id.toString();
    }

    @AfterEach
    public void tearDown() {
        orgsRepository.deleteAll();
    }

    @Test
    @DisplayName("Update Departments: Basic successful handling when id and parent is set correctly")
    void testSuccessfulHandling() {
        String parentOrgName = "NaUKMA";
        String departmentName = "FSNST";


        UpdateDepartment command = new UpdateDepartment();
        command.setName(departmentName);
        command.setParent(parentOrgName);
        command.setId(idToUpdate);

        Result<DepartmentUpdated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        Department createdDepartment = departmentsRepository.findByName(departmentName);
        Assertions.assertTrue(createdDepartment != null);
        Assertions.assertEquals(departmentName, createdDepartment.name);
    }

}
