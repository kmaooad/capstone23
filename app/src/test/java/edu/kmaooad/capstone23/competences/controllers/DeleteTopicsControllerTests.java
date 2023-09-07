package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.competences.events.TopicDeleted;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteTopicsControllerTests {

    @Test
    @DisplayName("Delete Topic: Basic")
    public void testBasicOrgCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "64f83a309682b0261c7bd24e");
        //jsonAsMap.put("");

        // System.out.println!("");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/topics/delete")
                .then()
                .statusCode(200);
    }

    // @Test
    // @DisplayName("Delete Topic Relation: Basic")
    // public void testTopicRelationCreation() {
    //     Map<String, Object> jsonAsMap = new HashMap<>();
    //     jsonAsMap.put("topicName", "food");
    //     //jsonAsMap.put("");

    //     String result = given()
    //             .contentType("application/json")
    //             .body(jsonAsMap)
    //             .when()
    //             .post("/topics/Delete")
    //             .then()
    //             .statusCode(200)
    //             .extract()
    //             .path("topic")
    //             ;

    //     Map<String, Object> jsonAsMap2 = new HashMap<>();
    //     jsonAsMap2.put("topicName", "fruits");
    //     jsonAsMap2.put("parentTopic", result);
    //     //jsonAsMap.put("");

    //     given()
    //             .contentType("application/json")
    //             .body(jsonAsMap2)
    //             .when()
    //             .post("/topics/Delete")
    //             .then()
    //             .statusCode(200)
    //             ;

    // }
}
