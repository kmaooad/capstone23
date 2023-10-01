package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.AddLogo;
import edu.kmaooad.capstone23.departments.commands.UpdateDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentUpdated;
import edu.kmaooad.capstone23.departments.events.LogoAdded;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.handlers.CreateOrgHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.io.File;

@QuarkusTest
public class AddLogoHandlerTest {
    @Inject
    AddLogoHandler handler;

    @Inject
    DepartmentsRepository departmentsRepository;

    private String idToUpdate;

    @BeforeEach
    void setUp() {
        departmentsRepository.deleteAll();
        Department department = new Department();

        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        departmentsRepository.insert(department);

        idToUpdate = department.id.toString();
    }

    @Test
    @DisplayName("Add Logo: Basic")
    public void testBasicAddLogo() {
        AddLogo command = new AddLogo();
        command.setDepartmentId(idToUpdate);

        File logoFile = new File("src/test/resources/departments/img.png");

        command.setLogo(logoFile);
        command.setLogoName("img.png");

        Result<LogoAdded> result = handler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), null);

        Department department = departmentsRepository.findById(idToUpdate);

        Assertions.assertEquals(department.logo.fileName, "img.png");

        Assertions.assertNotNull(department.logo.file);
    }

}
