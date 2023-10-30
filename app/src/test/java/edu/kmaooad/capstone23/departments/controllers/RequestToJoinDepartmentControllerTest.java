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
public class RequestToJoinDepartmentControllerTest {

    private String idToUpdate;

    @Inject
    DepartmentDriver departmentDriver;

    @BeforeEach
    void setUp() {
        Department department = departmentDriver.createDepartment();
        idToUpdate = department.id.toString();
    }


    @Test
    @DisplayName("Create Request to Join Department: Basic")
    public void testBasicRequestToJoinDepartment() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String userName = "user1";
        String departmentId = idToUpdate;

        jsonAsMap.put("userName", userName);
        jsonAsMap.put("departmentId", departmentId);

        given()
            .contentType("application/json")
            .body(jsonAsMap)
            .when()
            .post("/departments/request")
            .then()
            .statusCode(200);
    }


    @Test
    @DisplayName("Create Request to Join Department: No such department")
    public void testRequestToJoinDepartmentWithNonExistentId() {
        String nonexistentId = "64fbb243275c1111167b87a3";
        String userName = "user1";

        given()
            .contentType("application/json")
            .body("{\"userName\":\"" + userName + "\",\"departmentId\":\"" + nonexistentId + "\"}")
            .when()
            .post("/departments/request")
            .then()
            .statusCode(400);
    }


}
