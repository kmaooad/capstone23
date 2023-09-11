package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateDepartmentControllerTests {
    private String idToUpdate;

    @Inject
    DepartmentsRepository departmentsRepository;

    @BeforeEach
    void setUp() {
        Department department = new Department();

        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        departmentsRepository.insert(department);

        idToUpdate = department.id.toString();
    }


    @Test
    @DisplayName("Update Department: Basic")
    public void testBasicDeptsUpdate() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String newName = "FEN";
        String newDescription = "Faculty of Informatics 2";
        String newParent = "NaUKMA";

        jsonAsMap.put("name", newName);
        jsonAsMap.put("description", newDescription);
        jsonAsMap.put("parent", newParent);
        jsonAsMap.put("id", idToUpdate);

        given()
            .contentType("application/json")
            .body(jsonAsMap)
            .when()
            .post("/departments/update")
            .then()
            .statusCode(200);
    }


    @Test
    @DisplayName("Update Department: Id not found")
    public void testDeptsUpdateWithIdNotFound() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String newName = "FEN";
        String newDescription = "Faculty of Informatics 2";
        String newParent = "NaUKMA";
        String fakeId = "123456789";

        jsonAsMap.put("name", newName);
        jsonAsMap.put("description", newDescription);
        jsonAsMap.put("parent", newParent);
        jsonAsMap.put("id", fakeId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/update")
                .then()
                .statusCode(500);
    }


    @Test
    @DisplayName("Update Department: Parent name validation")
    public void testDeptsUpdateWithParentNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String newName = "FEN";
        String newDescription = "Faculty of Informatics 2";
        String fakeParent = "123456789";

        jsonAsMap.put("name", newName);
        jsonAsMap.put("description", newDescription);
        jsonAsMap.put("parent", fakeParent);
        jsonAsMap.put("id", idToUpdate);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/update")
                .then()
                .statusCode(400);
    }


}