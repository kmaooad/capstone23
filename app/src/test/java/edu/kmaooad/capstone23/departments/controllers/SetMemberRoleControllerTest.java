package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.departments.dal.*;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SetMemberRoleControllerTest {
    private String departmentId;

    private String userName;
    @Inject
    DepartmentsRepository departmentsRepository;

    @BeforeEach
    void setUp() {
        Department department = new Department();
        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        department.members = new ArrayList<>();

        Member member = new Member();
        member.userName = "Initial Member";
        member.role = "Initial Role";
        department.members.add(member);
        departmentsRepository.insert(department);

        departmentId = department.id.toString();
        userName = member.userName;
    }


    @Test
    @DisplayName("SetMemberRoleController should return 200")
    void setMemberRoleControllerShouldReturn200() {
        Map<String, String> jsonAsMap = new HashMap<>();
        jsonAsMap.put("departmentId", departmentId);
        jsonAsMap.put("userName", userName);
        jsonAsMap.put("role", "New Role");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/set-member-role")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("SetMemberRoleController member not found")
    void setMemberRoleControllerMemberNotFound() {
        Map<String, String> jsonAsMap = new HashMap<>();
        jsonAsMap.put("departmentId", departmentId);
        jsonAsMap.put("userName", "Not Found");
        jsonAsMap.put("role", "New Role");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/set-member-role")
                .then()
                .statusCode(400);
    }
}