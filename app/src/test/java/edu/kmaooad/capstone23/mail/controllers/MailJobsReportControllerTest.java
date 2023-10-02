package edu.kmaooad.capstone23.mail.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class MailJobsReportControllerTest {
    private String testEmail = "naukma@ukr.net";
    private int count = 5;

    @Test
    @DisplayName("Mail jobs report: valid input")
    public void testBasicMailReport() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("recipientEmail", testEmail);
        jsonAsMap.put("recordsCount", count);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/mail/jobs/report")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Mail jobs report: invalid input")
    public void testBasicMailReportWithValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("test@gmail.com", -5);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/mail/jobs/report")
                .then()
                .statusCode(400);
    }
}
