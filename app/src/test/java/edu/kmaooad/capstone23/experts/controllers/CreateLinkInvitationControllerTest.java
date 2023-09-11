package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.experts.ExpertType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateLinkInvitationControllerTest {

    @Test
    @DisplayName("Test link was successfully created")
    public void testLinkCreated() {
        var jsonBody = new HashMap<String, Object>();
        jsonBody.put("email", "test@gmail.com");
        jsonBody.put("expertType", ExpertType.EDUCATION.toString());

        given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/experts/create/invitation")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Test email validation")
    public void testEmailValidation() {
        var jsonBody = new HashMap<String, Object>();
        jsonBody.put("email", "test");
        jsonBody.put("expertType", ExpertType.EDUCATION.toString());

        given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/experts/create/invitation")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Test expertType validation")
    public void testExpertTypeValidation() {
        var jsonBody = new HashMap<String, Object>();
        jsonBody.put("email", "test@gmail.com");

        given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/experts/create/invitation")
                .then()
                .statusCode(400);
    }


}
