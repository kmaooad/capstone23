package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SetHiringStatusOnControllerTest {
    private String idToUpdate;

    @Inject
    DepartmentDriver departmentDriver;

    @BeforeEach
    void setUp() {
        Department department = departmentDriver.createDepartment();
        idToUpdate = department.id.toString();
    }


    @Test
    @DisplayName("Set hiring status on")
    void setHiringStatusOn() {
        Map<String, String> jsonAsMap = new HashMap<>();
        jsonAsMap.put("departmentId", idToUpdate);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when().post("/departments/set-hiring-status-on")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("Set hiring status on with wrong id")
    void setHiringStatusOnWithWrongId() {
        Map<String, String> jsonAsMap = new HashMap<>();
        jsonAsMap.put("departmentId", "64fbb243275c1111167b87a3");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when().post("/departments/set-hiring-status-on")
                .then()
                .statusCode(400);
    }
}