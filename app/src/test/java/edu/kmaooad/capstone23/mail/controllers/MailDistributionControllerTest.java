package edu.kmaooad.capstone23.mail.controllers;

import edu.kmaooad.capstone23.mail.dal.TypeOfDistribution;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class MailDistributionControllerTest {
    private TypeOfDistribution testType = TypeOfDistribution.ORGS;
    private String testBodyMessage = "We want to inform you that something is changed!";

    @Test
    @DisplayName("Mail distribution: empty repo")
    public void testBasicMailDistribution() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("messageBody", testBodyMessage);
        jsonAsMap.put("targetGroup", testType);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/mail/distribution")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Mail distribution: invalid input")
    public void testBasicMailDistributionWithValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("messageBody", testBodyMessage);
        jsonAsMap.put("targetGroup", 2);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/mail/distribution")
                .then()
                .statusCode(400);
    }
}
