package edu.kmaooad.capstone23.orgs.members.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateMemberControllerTest {
    @Inject
    CommandHandler<CreateOrg, OrgCreated> orgCommandHandler;

    private ObjectId createdOrgId;

    @BeforeEach
    void setUp() {
        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA");

        Result<OrgCreated> result = orgCommandHandler.handle(command);
        createdOrgId = new ObjectId(result.getValue().getOrgId());
    }

    @Test
    @DisplayName("Create Member: Basic")
    public void testBasicOrgCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", createdOrgId.toString());
        jsonAsMap.put("email", "email@email.com");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Member: Email validation")
    public void testOrgCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", createdOrgId);
        jsonAsMap.put("email", "email.com");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create")
                .then()
                .statusCode(400);
    }
}
