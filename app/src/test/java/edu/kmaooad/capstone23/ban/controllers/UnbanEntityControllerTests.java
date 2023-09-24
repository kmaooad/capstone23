package edu.kmaooad.capstone23.ban.controllers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.common.constraint.Assert;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UnbanEntityControllerTests {


    public String createOrg() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "NaUKMA");
        jsonAsMap.put("industry", "Education");
        jsonAsMap.put("website", "https://www.ukma.edu.ua/eng/");

        return given().contentType("application/json").body(jsonAsMap).when().post("/orgs/create").then().statusCode(200).extract().path("orgId");
    }

    public String createDepartment(String parentName) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "FI");
        jsonAsMap.put("description", "Faculty of Informatics");
        jsonAsMap.put("parent", parentName);

        return given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/create")
                .then()
                .statusCode(200)
                .extract().path("id");
    }

    public String createMember(String orgId) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", orgId);
        jsonAsMap.put("email", "email@email.com");

        return given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create")
                .then()
                .statusCode(200)
                .extract().path("memberId");
    }

    public String createEntity(BannedEntityType entityType, String parentId) {
        return switch (entityType) {
            case Organization -> createOrg();
            case Department -> createDepartment(parentId);
            case Member -> createMember(parentId);

        };
    }

    public void testSuccessfulEntityUnban(BannedEntityType entityType, String entityId) {
        Map<String, Object> banCommand = new HashMap<>();
        banCommand.put("reason", "Hello there");
        banCommand.put("entityType", entityType.toString());
        banCommand.put("entityId", entityId);

        given().contentType("application/json").body(banCommand).when().post("/ban").then().statusCode(200);

        Map<String, Object> unbanCommand = new HashMap<>();
        unbanCommand.put("entityType", entityType.toString());
        unbanCommand.put("entityId", entityId);
        
        Object banId;
        
        banId = given().contentType("application/json").body(unbanCommand).when().post("/unban").then().statusCode(200)
            .extract().path("id");
        Assertions.assertNotNull(banId);

        banId = given().contentType("application/json").body(unbanCommand).when().post("/unban").then().statusCode(200)
            .extract().path("id");
        Assertions.assertNull(banId);
    }

    @Test
    @DisplayName("Unban Org: Basic Test")
    public void testSuccessfulOrgUnban() {
        var entityId = createOrg();
        testSuccessfulEntityUnban(BannedEntityType.Organization, entityId);
    }

    @Test
    @DisplayName("Unban Department: Basic Test")
    public void testSuccessfulDepartmentUnban() {
        createOrg();
        var entityId = createDepartment("NaUKMA");
        testSuccessfulEntityUnban(BannedEntityType.Department, entityId);
    }

    @Test
    @DisplayName("Unban Member: Basic Test")
    public void testSuccessfulMemberUnban() {
        var orgId = createOrg();
        var entityId = createMember(orgId);
        testSuccessfulEntityUnban(BannedEntityType.Member, entityId);
    }
}
