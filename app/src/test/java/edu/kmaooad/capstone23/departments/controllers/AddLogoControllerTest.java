package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AddLogoControllerTest {
    private String idToUpdate;

    @Inject
    DepartmentDriver departmentDriver;

    @BeforeEach
    void setUp() {
        Department department = departmentDriver.createDepartment();
        idToUpdate = department.id.toString();
    }

    @Test
    @DisplayName("Add Logo: Basic")
    public void testBasicAddLogo() {
        Map<String, Object> jsonAsMap = new HashMap<>();


        jsonAsMap.put("departmentId", idToUpdate);

        String logo = "https://img.freepik.com/free-vector/bird-colorful-logo-gradient-vector_343694-1365.jpg";

        jsonAsMap.put("logo", logo);

        String logoName = "img.png";

        jsonAsMap.put("logoName", logoName);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/add-logo")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("Add Logo: Invalid Department Id")
    public void testInvalidDepartmentIdAddLogo() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String departmentId = "64fbb243275c1111167b87a3";

        jsonAsMap.put("departmentId", departmentId);

        String logo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAA";

        jsonAsMap.put("logo", logo);

        String logoName = "img.png";

        jsonAsMap.put("logoName", logoName);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/add-logo")
                .then()
                .statusCode(400);
    }
}