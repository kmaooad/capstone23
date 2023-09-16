package edu.kmaooad.capstone23.students.controllers;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateStudentControllerTest {
    @Test
    @DisplayName("Create students from csv: Successful")
    public void testStudentsCreationFromCSVSuccess() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/success.csv"), "text/csv")
                .when()
                .post("/students/create_csv")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create students from csv: Incorrect file type")
    public void testStudentsCreationFromCSVIncorrectType() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/incorrect_type.json"), "application/json")
                .when()
                .post("/students/create_csv")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create students from csv: Incorrect date format")
    public void testStudentsCreationFromCSVIncorrectDateFormat() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/incorrect_date_format.csv"), "text/csv")
                .when()
                .post("/students/create_csv")
                .then()
                .statusCode(400);
    }
}
