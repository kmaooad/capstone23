package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.AddLogo;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.LogoAdded;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

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


        String logo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAA";


        command.setLogo(logo);
        command.setLogoName("img.png");

        Result<LogoAdded> result = handler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), null);

        Department department = departmentsRepository.findById(idToUpdate);

        Assertions.assertEquals(department.logo.fileName, "img.png");

        Assertions.assertNotNull(department.logo.file);

        Assertions.assertEquals(department.logo.file, logo);
    }

}
