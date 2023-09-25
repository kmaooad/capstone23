package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SetHiringStatusOnControllerTest {
    private String idToUpdate;

    @Inject
    OrgsRepository orgsRepository;

    @BeforeEach
    void setUp() {
        orgsRepository.deleteAll();
        Org org = new Org();
        org.name = "Initial Organization";
        orgsRepository.insert(org);

        idToUpdate = org.id.toString();
    }


    @Test
    @DisplayName("Set hiring status on")
    void setHiringStatusOn() {
        Map<String, String> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgId", idToUpdate);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when().post("/orgs/set-hiring-status-on")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("Set hiring status on with wrong id")
    void setHiringStatusOnWithWrongId() {
        Map<String, String> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgId", "64fbb243275c1111167b87a3");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when().post("/orgs/set-hiring-status-on")
                .then()
                .statusCode(400);
    }
}
