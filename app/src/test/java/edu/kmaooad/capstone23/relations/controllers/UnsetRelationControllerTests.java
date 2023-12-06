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

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String SET_RELATION_ENDPOINT = "/relations/set";
    private static final String UNSET_RELATION_ENDPOINT = "/relations/unset";

    @Test
    @DisplayName("Test Unsetting Relation Endpoint")
    public void testUnsetRelationEndpoint() {
        ObjectId relationToUnsetID = createDefaultRelation();
        Assertions.assertNotNull(relationToUnsetID);
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", relationToUnsetID.toHexString());
        jsonAsMap.put("collectionName1", "courses");
        jsonAsMap.put("collectionName2", "projects");

        postAndAssert(jsonAsMap, UNSET_RELATION_ENDPOINT, 200);
    }

    @Test
    @DisplayName("Test Invalid Unset Endpoint - Non-existent Relation ID")
    public void testInvalidUnsetForNonExistentRelation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "5f7e47fc8e1f7112d73c93b0");

        postAndAssert(jsonAsMap, UNSET_RELATION_ENDPOINT, 400);
    }

    private ObjectId createDefaultRelation() {
        Map<String, Object> jsonAsMap = buildRelationMap(
                "courses", "projects", "5f7e47fc8e1f7112d73c92a1", "1a4cd132b123a1aa3bc2d142"
        );

        String id = given()
                .contentType(CONTENT_TYPE_JSON)
                .body(jsonAsMap)
                .when()
                .post(SET_RELATION_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .path("id");
        return new ObjectId(id);
    }

    private Map<String, Object> buildRelationMap(String collectionName1, String collectionName2, String objectId1, String objectId2) {
        Map<String, Object> map = new HashMap<>();
        map.put("collectionName1", collectionName1);
        map.put("collectionName2", collectionName2);
        map.put("objectToConnectId1", objectId1);
        map.put("objectToConnectId2", objectId2);
        return map;
    }

    private void postAndAssert(Map<String, Object> requestBody, String endpoint, int expectedStatusCode) {
        given()
                .contentType(CONTENT_TYPE_JSON)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .statusCode(expectedStatusCode);
    }
}
