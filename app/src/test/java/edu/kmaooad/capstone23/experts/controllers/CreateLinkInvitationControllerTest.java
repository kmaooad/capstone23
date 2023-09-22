package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.experts.ExpertType;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateLinkInvitationControllerTest {
    @Inject
    OrgsRepository orgsRepository;

    private static final String ORG_NAME = "My org";

    @BeforeEach
    public void beforeEach() {
        Org org = new Org();
        org.name = ORG_NAME;
        orgsRepository.persist(org);
    }

    @Test
    @DisplayName("Test link was successfully created")
    public void testLinkCreated() {
        System.setProperty("quarkus.http.host", "localhost:8081");
        var jsonBody = new HashMap<String, Object>();
        jsonBody.put("email", "test@gmail.com");
        jsonBody.put("expertType", ExpertType.EDUCATION.toString());
        jsonBody.put("expertName", "Oleg");
        jsonBody.put("orgName", ORG_NAME);

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
        jsonBody.put("expertName", "Oleg");
        jsonBody.put("orgName", "Oleg org");

        given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/experts/create/invitation")
                .then()
                .statusCode(400);
    }

    @Test
    public void expertNameValidation() {
        var jsonBody = new HashMap<String, Object>();
        jsonBody.put("email", "test@gmail.com");
        jsonBody.put("expertType", ExpertType.EDUCATION.toString());
        jsonBody.put("expertName", "");
        jsonBody.put("orgName", ORG_NAME);

        given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/experts/create/invitation")
                .then()
                .statusCode(400);
    }

    @Test
    public void orgNameValidation() {
        var jsonBody = new HashMap<String, Object>();
        jsonBody.put("email", "test@gmail.com");
        jsonBody.put("expertType", ExpertType.EDUCATION.toString());
        jsonBody.put("expertName", "Expert Name");
        jsonBody.put("orgName", "Invalid org");

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
        jsonBody.put("expertName", "Expert Name");
        jsonBody.put("orgName", ORG_NAME);

        given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/experts/create/invitation")
                .then()
                .statusCode(400);
    }

    @AfterEach
    public void teardown() {
        orgsRepository.deleteAll();
    }
}
