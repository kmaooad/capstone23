package edu.kmaooad.capstone23.orgs.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateExpertControllerTest {

//    @Test
//    @DisplayName("Create Expert: Basic")
//    public void testBasicExpertCreation() {
//        Map<String, Object> jsonAsMap = new HashMap<>();
//        jsonAsMap.put("expertName", "Arkhypchuk_Stepanenko");
//
//        given()
//                .contentType("application/json")
//                .body(jsonAsMap)
//                .when()
//                .post("/expert/create")
//                .then()
//                .statusCode(200);
//    }

    @Test
    @DisplayName("Create Expert: Name validation")
    public void testExpertCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertName", "Arkhypchuk_Stepanenko_Fake");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/expert/create")
                .then()
                .statusCode(400);
    }
}
