package edu.kmaooad.capstone23.ban.controllers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class IsBannedControllerTests {

    public String createOrg() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgName", "NaUKMA");
        jsonAsMap.put("industry", "Education");
        jsonAsMap.put("website", "https://www.ukma.edu.ua/eng/");

        return given().contentType("application/json").body(jsonAsMap).when().post("/orgs/create").then().statusCode(200).extract().path("orgId");
    }


    public void banOrg(String orgId) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("reason", "Hello there");
        jsonAsMap.put("entityType", BannedEntityType.Organization.name());
        jsonAsMap.put("entityId", orgId);

        given().contentType("application/json").body(jsonAsMap).when().post("/ban").then().statusCode(200);
    }

    @Test
    @DisplayName("Is banned Org: Basic Test")
    public void testTruthyIsBanned() {
        var org = createOrg();
        banOrg(org);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("entityType", BannedEntityType.Organization.name());
        jsonAsMap.put("entityId", org);

        boolean value = given().contentType("application/json").body(jsonAsMap).when().post("/is-banned").then().statusCode(200)
                .extract().path("value");

        Assertions.assertTrue(value);
    }


}
