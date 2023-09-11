package edu.kmaooad.capstone23.competences.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class UpdateProjControllerTests {

    @Test
    @DisplayName("Update Proj: Basic")
    public void testBasicProjUpdate() {
        ObjectId createdId = createProj();
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", createdId.toHexString());
        jsonAsMap.put("name", "Updated Project Name");
        jsonAsMap.put("description", "Updated project description");
        jsonAsMap.put("skills", List.of("1a4cd132b123a1aa3bc2d142"));
        jsonAsMap.put("skillSets", List.of("5f7e47fc8e1f7112d73c92a1"));

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/projs/update")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Update Proj: Name validation")
    public void testProjUpdateWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "2"); 
        jsonAsMap.put("name", "Updated_Project_2");
        jsonAsMap.put("description", "Updated project 2 description");
        jsonAsMap.put("skills", Arrays.asList("React", "JavaScript"));
        jsonAsMap.put("skillSets", Arrays.asList("Frontend Development"));

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/projs/update")
                .then()
                .statusCode(400);
    }

    private ObjectId createProj() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "WEB");
        jsonAsMap.put("description", "This project is for students");
        jsonAsMap.put("skills", List.of("5f7e47fc8e1f7112d73c92a1"));
        jsonAsMap.put("skillSets", List.of("1a4cd132b123a1aa3bc2d142"));

        String objectId =  given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/projs/create")
                .then()
                .statusCode(200)
                .extract()
                .path("projId");

        return new ObjectId(objectId);
    }
}