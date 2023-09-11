package edu.kmaooad.capstone23.competences.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class DeleteProjControllerTests {

    @Test
    @DisplayName("Delete Proj: Basic")
    public void testBasicProjDeletion() {
        ObjectId idOfCreated = createProj();
        assertNotNull(idOfCreated);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idOfCreated.toHexString());
        System.err.println("RESOIBSODA" + idOfCreated.toHexString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/projs/delete")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete Proj: Invalid ID")
    public void testProjDeletionWithInvalidId() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        String invalidId = "abcde";
        jsonAsMap.put("id", invalidId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/projs/delete")
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