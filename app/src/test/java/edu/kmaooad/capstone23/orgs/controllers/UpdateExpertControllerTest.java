package edu.kmaooad.capstone23.orgs.controllers;

import io.quarkus.test.junit.QuarkusTest;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateExpertControllerTest {

    @Test
    @DisplayName("Update Expert: Basic")
    public void testBasicExpertUpdate() {
        ObjectId expertId = createTestExpert("John Doe");

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", expertId.toHexString());
        jsonAsMap.put("expertName", "Jane Doe");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/update")
                .then()
                .statusCode(200);
    }

    private ObjectId createTestExpert(String name) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertName", name);
        //jsonAsMap.put("orgId", createTestOrg("Random Org"));

        String objectId = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/create")
                .then()
                .statusCode(200)
                .extract()
                .path("expertId");

        return new ObjectId(objectId);
    }
}
