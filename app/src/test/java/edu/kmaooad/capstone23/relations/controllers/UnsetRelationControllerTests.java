package edu.kmaooad.capstone23.relations.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class UnsetRelationControllerTests {

    @Test
    @DisplayName("Test Unsetting Relation Endpoint")
    void testUnsetRelationEndpoint() {
        given()
                .when().delete("/relations/unset")
                .then()
                .statusCode(200)
                .body("message", is("Relation unset successfully."));
    }

    @Test
    @DisplayName("Test Invalid Unset Endpoint - Non-existent Relation ID")
    void testInvalidUnsetForNonExistentRelation() {
        given()
                .pathParam("id", new ObjectId("5f7e47fc8e1f7112d73c93b0").toString())  //  non-existent
                .when().delete("/relations/unset/{id}")
                .then()
                .statusCode(400)
                .body("message", is("Relation not found."));
    }
}
