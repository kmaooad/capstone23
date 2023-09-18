package edu.kmaooad.capstone23.relations.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UnsetRelationControllerTests {
    @Test
    @DisplayName("Test Unsetting Relation Endpoint")
    public void testUnsetRelationEndpoint() {
        ObjectId relationToUnsetID = createDefaultRelation();
        Assertions.assertNotNull(relationToUnsetID);
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", relationToUnsetID.toHexString());
        jsonAsMap.put("collectionName1", "courses");
        jsonAsMap.put("collectionName2", "projects");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/relations/unset")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Test Invalid Unset Endpoint - Non-existent Relation ID")
    public void testInvalidUnsetForNonExistentRelation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "5f7e47fc8e1f7112d73c93b0");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/relations/unset")
                .then()
                .statusCode(400);
    }

    private ObjectId createDefaultRelation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("collectionName1", "courses");
        jsonAsMap.put("collectionName2", "projects");
        jsonAsMap.put("objectToConnectId1", "5f7e47fc8e1f7112d73c92a1");
        jsonAsMap.put("objectToConnectId2", "1a4cd132b123a1aa3bc2d142");

        String id = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/relations/set")
                .then()
                .statusCode(200)
                .extract()
                .path("id");
        return new ObjectId(id);
    }

}
