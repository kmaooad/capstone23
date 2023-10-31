package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteDepartmentControllerTests {
    private String departmentId;

    @Inject
    DepartmentDriver departmentDriver;

    @BeforeEach
    void setUp() {
        Department department = departmentDriver.createDepartment();
        departmentId = department.id.toString();
    }

    @Test
    @DisplayName("Delete Department: Basic")
    public void testBasicDeptsDeletion() {
        given()
                .contentType("application/json")
                .body("{\"id\":\"" + departmentId + "\"}")
                .when()
                .post("/departments/delete")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete Department: No such department")
    public void testDeptsDeletionWithNonExistentId() {
        String nonexistentId = "64fbb243275c1111167b87a3";

        given()
                .contentType("application/json")
                .body("{\"id\":\"" + nonexistentId + "\"}")
                .when()
                .post("/departments/delete")
                .then()
                .statusCode(400);
    }
}


