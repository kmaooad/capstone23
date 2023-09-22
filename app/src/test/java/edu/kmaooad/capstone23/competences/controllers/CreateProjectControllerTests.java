package edu.kmaooad.capstone23.competences.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateProjectControllerTests {

    @Test
    @DisplayName("Basic")
    public void testBasicProjCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "OOK");
        jsonAsMap.put("description", "This project is for students");
        jsonAsMap.put("skills", List.of("5f7e47fc8e1f7112d73c92a1"));
        jsonAsMap.put("skillSets", List.of("1a4cd132b123a1aa3bc2d142"));

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/projs/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Name validation")
    public void testProjCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "WEB");
        jsonAsMap.put("description", "This project is for students");
        jsonAsMap.put("skills", List.of("5f7e47fc8e1f7112d73c92a1"));
        jsonAsMap.put("skillSets", List.of("1a4cd132b123a1aa3bc2d142"));

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/projs/create")
                .then()
                .statusCode(200);
    }
}