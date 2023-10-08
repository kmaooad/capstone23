package edu.kmaooad.capstone23.access_rules.controllers;

import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class DeleteAccessRuleControllerTests {

    @Inject
    AccessRuleRepository accessRuleRepository;

    private String accessRuleId;

    @BeforeEach
    private void prepare() {
        AccessRule rule = new AccessRule();
        accessRuleRepository.persist(rule);
        accessRuleId = rule.id.toString();
    }

    @Test
    @DisplayName("Delete Access Rule: valid ID")
    public void deleteRuleValidId() {
        deleteAccessRule(accessRuleId, 200);
    }


    private void deleteAccessRule(String ruleId, int expectedResult){
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", ruleId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/accessrules/delete")
                .then()
                .statusCode(expectedResult);
    }

}

