package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.Request;
import edu.kmaooad.capstone23.departments.dal.RequestsRepository;
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
public class ApproveJoinRequestControllerTest {
    private String idToUpdate;

    @Inject
    RequestsRepository requestsRepository;

    @Inject
    DepartmentDriver departmentDriver;

    @BeforeEach
    void setUp() {
        Department department = departmentDriver.createDepartment();

        Request request = new Request();
        request.userName = "user1@umkma.edu";
        request.departmentId = department.id.toString();
        request.status = "pending";
        requestsRepository.insert(request);

        idToUpdate = request.id.toString();
    }


    @Test
    @DisplayName("Approve Join Request: Basic")
    public void testBasicApproveJoinRequest() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String requestId = idToUpdate;

        jsonAsMap.put("requestId", requestId);

        given()
            .contentType("application/json")
            .body(jsonAsMap)
            .when()
            .post("/departments/approve-request")
            .then()
            .statusCode(200);
    }


    @Test
    @DisplayName("Approve Join Request: No such request")
    public void testApproveJoinRequestWithNonExistentId() {
        String nonexistentId = "64fbb243275c1111167b87a3";

        given()
            .contentType("application/json")
            .body("{\"requestId\":\"" + nonexistentId + "\"}")
            .when()
            .post("/departments/approve-request")
            .then()
            .statusCode(400);
    }
}