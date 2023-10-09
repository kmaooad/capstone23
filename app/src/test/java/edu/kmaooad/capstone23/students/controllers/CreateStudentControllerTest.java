package edu.kmaooad.capstone23.students.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

@QuarkusTest
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

    @Test
    @DisplayName("Create students from csv: not enough fields")
    public void testsStudentsCreationFromCSVNotEnoughFields() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/not_enough_fields.csv"), "text/csv")
                .when()
                .post("students/create_csv")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create students from csv: too many fields")
    public void testsStudentsCreationFromCSVTooManyFields() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/too_many_fields.csv"), "text/csv")
                .when()
                .post("students/create_csv")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create students from csv: contains empty string")
    public void testsStudentsCreationFromCSVContainsEmptyString() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/contains_empty_strings.csv"), "text/csv")
                .when()
                .post("students/create_csv")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create students from csv: invalid email")
    public void testsStudentsCreationFromCSVInvalidEmail() {
        given()
                .multiPart("csvFile", new File("src/test/resources/students/invalid_email.csv"), "text/csv")
                .when()
                .post("students/create_csv")
                .then()
                .statusCode(400);
    }

}
