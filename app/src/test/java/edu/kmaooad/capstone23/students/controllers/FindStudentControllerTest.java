package edu.kmaooad.capstone23.students.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FindStudentControllerTest {

    @BeforeEach
    public void setup() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/find_init.csv"), "text/csv")
                .when()
                .post("/students/create_csv")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Find all students by default")
    public void testFindAllStudents() {
        given()
                .contentType("application/json")
                .body(Map.of())
                .when()
                .post("/search/students")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Find students by first name")
    public void testFindStudentsByFirstName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "Iva");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/students")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Find students by middle name")
    public void testFindStudentsByMiddleName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("middleName", "Oleksandrovy");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/students")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Find students by last name")
    public void testFindStudentsByLastName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("lastName", "Dobrovolskyiii");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/students")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Find students by full name: First name")
    public void testFindStudentsByFullNameFirstName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("fullName", "Iva");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/students")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Find students by full name: Middle name")
    public void testFindStudentsByFullNameMiddleName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("fullName", "Oleksandrovy");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/students")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Find students by full name: Last name")
    public void testFindStudentsByFullNameLastName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("fullName", "Dobrovolskyiii");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/students")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Find students by email")
    public void testFindStudentsByEmail() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("email", "ivan.do");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/students")
                .then()
                .statusCode(200);
    }
}
