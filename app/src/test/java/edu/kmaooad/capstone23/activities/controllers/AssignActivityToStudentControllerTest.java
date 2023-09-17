package edu.kmaooad.capstone23.activities.controllers;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


import edu.kmaooad.capstone23.students.dal.Student;
import edu.kmaooad.capstone23.students.dal.StudentRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class AssignActivityToStudentControllerTest {

    private ObjectId studentId;
    private ObjectId activityId;

    @Inject
    StudentRepository studentRepository;

    @BeforeEach
    void setup() {
        Student student = new Student("Vasya");
        studentRepository.insert(student);
        studentId = student.id;
    }

    @Test
    @DisplayName("Assign activity to an existing student")
    public void testAssignActivityToExistingStudent() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("studentId", studentId.toString());
        jsonAsMap.put("activityId", "WEB activity");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/assign/student")
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Assign activity to a non-existing student")
    public void testAssignActivityToNonExistingStudent() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("studentId", "nonExistingSt");
        jsonAsMap.put("activityId", "WEB activity");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/assign/student")
                .then()
                .statusCode(500);
    }
}
