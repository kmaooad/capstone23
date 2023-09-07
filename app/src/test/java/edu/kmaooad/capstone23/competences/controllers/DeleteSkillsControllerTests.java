package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.competences.events.SkillDeleted;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteSkillsControllerTests {

    @Test
    @DisplayName("Delete Skill: Basic")
    public void testBasicOrgCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "64f83a309682b0261c7bd24e");
        //jsonAsMap.put("");

        // System.out.println!("");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skills/delete")
                .then()
                .statusCode(200);
    }

    // @Test
    // @DisplayName("Delete Skill Relation: Basic")
    // public void testSkillRelationCreation() {
    //     Map<String, Object> jsonAsMap = new HashMap<>();
    //     jsonAsMap.put("skillName", "food");
    //     //jsonAsMap.put("");

    //     String result = given()
    //             .contentType("application/json")
    //             .body(jsonAsMap)
    //             .when()
    //             .post("/skills/Delete")
    //             .then()
    //             .statusCode(200)
    //             .extract()
    //             .path("skill")
    //             ;

    //     Map<String, Object> jsonAsMap2 = new HashMap<>();
    //     jsonAsMap2.put("skillName", "fruits");
    //     jsonAsMap2.put("parentSkill", result);
    //     //jsonAsMap.put("");

    //     given()
    //             .contentType("application/json")
    //             .body(jsonAsMap2)
    //             .when()
    //             .post("/skills/Delete")
    //             .then()
    //             .statusCode(200)
    //             ;

    // }
}
