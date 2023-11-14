package edu.kmaooad.capstone23.relations.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SetRelationControllerTests {

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String RELATION_ENDPOINT = "/relations/set";

    @Test
    @DisplayName("Basic Relation Setting")
    public void testBasicRelationSetting() {
        Map<String, Object> jsonAsMap = buildRelationMap("courses", "projects", "5f7e47fc8e1f7112d73c92a1", "1a4cd132b123a1aa3bc2d142");
        postAndAssert(jsonAsMap, 200);
    }

    @Test
    @DisplayName("Missing Relation Details")
    public void testMissingRelationDetails() {
        Map<String, Object> jsonAsMap = buildPartialRelationMap("CollectionA", "5f7e47fc8e1f7112d73c92a1");
        postAndAssert(jsonAsMap, 400);
    }

    private Map<String, Object> buildRelationMap(String collectionName1, String collectionName2, String objectId1, String objectId2) {
        Map<String, Object> map = new HashMap<>();
        map.put("collectionName1", collectionName1);
        map.put("collectionName2", collectionName2);
        map.put("objectToConnectId1", objectId1);
        map.put("objectToConnectId2", objectId2);
        return map;
    }

    private Map<String, Object> buildPartialRelationMap(String collectionName, String objectId) {
        Map<String, Object> map = new HashMap<>();
        map.put("collectionName1", collectionName);
        map.put("objectToConnectId1", objectId);
        return map;
    }

    private void postAndAssert(Map<String, Object> requestBody, int expectedStatusCode) {
        given()
                .contentType(CONTENT_TYPE_JSON)
                .body(requestBody)
                .when()
                .post(RELATION_ENDPOINT)
                .then()
                .statusCode(expectedStatusCode);
    }
}

