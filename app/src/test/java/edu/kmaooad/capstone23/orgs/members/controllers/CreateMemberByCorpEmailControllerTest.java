package edu.kmaooad.capstone23.orgs.members.controllers;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.members.TestWithDbClearance;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateMemberByCorpEmailControllerTest extends TestWithDbClearance {

    private String orgEmailDomain;

    @BeforeEach
    void setUp() {
        orgsRepository.deleteAll();
        membersRepository.deleteAll();

        Org org = new Org();
        org.name = "Ubi";
        org.industry = "Entertainment";
        org.website = "https://www.ubisoft.com";
        org.emailDomain = "ubisoft.com";
        orgsRepository.insert(org);

        orgEmailDomain = org.emailDomain;
    }

    @Test
    @DisplayName("Create Member: Basic")
    public void testBasicMemberCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("corpEmail", "oneMoreCorpEmail@" + orgEmailDomain);
        jsonAsMap.put("isExpert", "true");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create/corp")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Member: Email validation")
    public void testMemberCreationWithEmailValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("corpEmail", orgEmailDomain);
        jsonAsMap.put("isExpert", "false");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create/corp")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create Member: Org does not exist")
    public void testMemberCreationWithOrgExistenceValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("corpEmail", "wrongCorpEmail@" + orgEmailDomain + "HELLO");
        jsonAsMap.put("isExpert", "false");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create/corp")
                .then()
                .statusCode(400);
    }
}
