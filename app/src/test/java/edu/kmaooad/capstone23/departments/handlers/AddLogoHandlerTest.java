package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.AddLogo;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import edu.kmaooad.capstone23.departments.events.LogoAdded;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

@QuarkusTest
public class AddLogoHandlerTest {
    @Inject
    AddLogoHandler handler;

    @Inject
    DepartmentService departmentService;

    @Inject
    DepartmentDriver departmentDriver;

    private String idToUpdate;

    @BeforeEach
    void setUp() {
        Department department = departmentDriver.createDepartment();
        idToUpdate = department.id.toString();
    }

    @Test
    @DisplayName("Add Logo: Basic")
    public void testBasicAddLogo() {
        AddLogo command = new AddLogo();
        command.setDepartmentId(idToUpdate);


        String logo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAA";


        command.setLogo(logo);
        command.setLogoName("img.png");

        Result<LogoAdded> result = handler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), null);

        Department department = departmentService.getDepartmentById(idToUpdate);

        Assertions.assertEquals(department.logo.fileName, "img.png");

        Assertions.assertNotNull(department.logo.file);

        Assertions.assertEquals(department.logo.file, logo);
    }

}
