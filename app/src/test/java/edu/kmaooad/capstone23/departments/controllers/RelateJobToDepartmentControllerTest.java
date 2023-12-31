package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RelateJobToDepartmentControllerTest {
    private String departmentId;

    private String jobId;
    @Inject
    DepartmentService departmentService;

    @Inject
    DepartmentDriver departmentDriver;

    @Inject
    JobRepository jobRepository;

    @BeforeEach
    void setUp() {
        jobRepository.deleteAll();

        Department department = departmentDriver.createDepartment();

        Job job = new Job();
        job.name = "Initial Job";
        job.active = true;
        jobRepository.insert(job);

        department.jobs.add(job.id.toString());
        departmentService.updateDepartment(department);

        departmentId = department.id.toString();
        jobId = job.id.toString();
    }


    @Test
    @DisplayName("Relate Job To Department: existed job")
    public void testBasicJobDepartmentConnectionCreation() {

        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("jobId", jobId);
        jsonAsMap.put("departmentId", departmentId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/relate-job-to-department")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("Relate Job To Department: notExisted job")
    public void testNotExistedJobDepartmentConnectionCreation() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        String id = "aaaaaaaaaaaaaaaaaaaaaaaa";
        jsonAsMap.put("jobId", id);
        jsonAsMap.put("departmentId", departmentId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/relate-job-to-department")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Relate Job To Department: notExisted department")
    public void testNotExistedDepartmentJobConnectionCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        String id = "64fbb243275c1111167b87a3";
        jsonAsMap.put("jobId", jobId);
        jsonAsMap.put("departmentId", id);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/relate-job-to-department")
                .then()
                .statusCode(400);
    }
}