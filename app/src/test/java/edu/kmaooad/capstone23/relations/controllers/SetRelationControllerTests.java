package edu.kmaooad.capstone23.relations.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SetRelationControllerTests {

    @Test
    @DisplayName("Basic Relation Setting")
    public void testBasicRelationSetting() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("collectionName1", "CollectionA");
        jsonAsMap.put("collectionName2", "CollectionB");
        jsonAsMap.put("objectToConnectId1", "5f7e47fc8e1f7112d73c92a1");
        jsonAsMap.put("objectToConnectId2", "1a4cd132b123a1aa3bc2d142");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/relations/set")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Missing Relation Details")
    public void testMissingRelationDetails() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("collectionName1", "CollectionA");
        // Missing part
        jsonAsMap.put("objectToConnectId1", "5f7e47fc8e1f7112d73c92a1");
        // Missing opart

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/relations/set")
                .then()
                .statusCode(400);
    }
}
