package edu.kmaooad.capstone23.ban.controllers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BanEntityControllerTests {


    public String createOrg() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "NaUKMA");

        return given().contentType("application/json").body(jsonAsMap).when().post("/orgs/create").then().statusCode(200).extract().path("orgId");
    }

    public String createDepartment(String parentName) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "FI");
        jsonAsMap.put("description", "Faculty of Informatics");
        jsonAsMap.put("parent", parentName);

        return given().contentType("application/json").body(jsonAsMap).when().post("/departments/create").then().statusCode(200).extract().path("id");
    }

    public String createMember(String orgId) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", orgId);
        jsonAsMap.put("email", "email@email.com");

        return given().contentType("application/json").body(jsonAsMap).when().post("/members/create").then().statusCode(200).extract().path("memberId");
    }

    public String createEntity(BannedEntityType entityType, String parentId) {
        switch (entityType) {
            case Organization:
                return createOrg();
            case Department:
                return createDepartment(parentId);
            case Member:
                return createMember(parentId);
            default:
                return null;
        }
    }

    public void testSuccessfulEntityBan(BannedEntityType entityType, String entityId) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("reason", "Hello there");
        jsonAsMap.put("entityType", entityType.toString());
        jsonAsMap.put("entityId", entityId);

        given().contentType("application/json").body(jsonAsMap).when().post("/ban").then().statusCode(200);
    }

    @Test
    @DisplayName("Ban Org: Basic Test")
    public void testSuccessfulOrgBan() {
        var entityId = createOrg();
        testSuccessfulEntityBan(BannedEntityType.Organization, entityId);
    }

    @Test
    @DisplayName("Ban Department: Basic Test")
    public void testSuccessfulDepartmentBan() {
        createOrg();
        var entityId = createDepartment("NaUKMA");
        testSuccessfulEntityBan(BannedEntityType.Department, entityId);
    }

    @Test
    @DisplayName("Ban Member: Basic Test")
    public void testSuccessfulMemberBan() {
        var orgId = createOrg();
        var entityId = createMember(orgId);
        testSuccessfulEntityBan(BannedEntityType.Member, entityId);
    }

    @Test
    @DisplayName("Ban Org: Org doesn't exist test")
    public void testUnsuccessfulOrgBan() {
        testUnsuccessfulEntityBan(BannedEntityType.Organization);
    }

    @Test
    @DisplayName("Ban Department: Department doesn't exist test")
    public void testUnsuccessfulDepartmentBan() {
        testUnsuccessfulEntityBan(BannedEntityType.Department);
    }

    @Test
    @DisplayName("Ban Member: Member doesn't exist test")
    public void testUnsuccessfulMemberBan() {
        testUnsuccessfulEntityBan(BannedEntityType.Member);
    }

    @Test
    @DisplayName("Ban Illegal Entity: Entity can't be banned")
    public void testIllegalEntityBan() {
        var entityType = "illegal";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("reason", "Hello there");
        jsonAsMap.put("entityType", entityType);
        jsonAsMap.put("entityId", new ObjectId().toHexString());

        given().contentType("application/json").body(jsonAsMap).when().post("/ban").then().statusCode(400);
    }

    public void testUnsuccessfulEntityBan(BannedEntityType entityType) {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("reason", "Hello there");
        jsonAsMap.put("entityType", entityType.toString());
        jsonAsMap.put("entityId", new ObjectId().toHexString());

        given().contentType("application/json").body(jsonAsMap).when().post("/ban").then().statusCode(400);
    }
}
