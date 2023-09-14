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

    @Test
    @DisplayName("Ban Org: Basic Test")
    public void testSuccessfulOrgBan() {
        var org = createOrg();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("reason", "Hello there");
        jsonAsMap.put("entityType", BannedEntityType.Organization.text);
        jsonAsMap.put("entityId", org);

        given().contentType("application/json").body(jsonAsMap).when().post("/ban").then().statusCode(200);
    }

    @Test
    @DisplayName("Ban Org: Org doesn't exist test")
    public void testUnsuccessfulOrgBan() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("reason", "Hello there");
        jsonAsMap.put("entityType", BannedEntityType.Organization.text);
        jsonAsMap.put("entityId", new ObjectId().toHexString());

        given().contentType("application/json").body(jsonAsMap).when().post("/ban").then().statusCode(400);
    }
}
