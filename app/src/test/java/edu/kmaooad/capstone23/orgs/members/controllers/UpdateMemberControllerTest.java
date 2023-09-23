package edu.kmaooad.capstone23.orgs.members.controllers;

import edu.kmaooad.capstone23.orgs.members.TestWithOrgSetUp;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateMemberControllerTest extends TestWithOrgSetUp {
    @Test
    @DisplayName("Update Member: Basic")
    public void testBasicMemberUpdate() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", createdOrgId.toString());
        jsonAsMap.put("email", "email@email123.com");

        var createdMember = createMember();

        jsonAsMap.put("id", createdMember);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/update")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Update Member: Email validation")
    public void testMemberUpdateWithEmailValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", createdOrgId.toString());
        jsonAsMap.put("email", "emailemail123");

        var createdMember = createMember();

        jsonAsMap.put("id", createdMember);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/update")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Update Member: Org does not exist")
    public void testMemberUpdateWithOrgExistenceValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        var newObjectId = new ObjectId();
        while (orgsRepository.findByIdOptional(newObjectId).isPresent())
            newObjectId = new ObjectId();
        jsonAsMap.put("orgId", newObjectId.toString());
        jsonAsMap.put("email", "email@a.com");

        var createdMember = createMember();

        jsonAsMap.put("id", createdMember);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/update")
                .then()
                .statusCode(400);
    }
}
