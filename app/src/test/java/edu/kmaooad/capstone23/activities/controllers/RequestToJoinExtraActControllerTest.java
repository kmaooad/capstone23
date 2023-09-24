package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ExtraAct;
import edu.kmaooad.capstone23.activities.dal.ExtraActRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RequestToJoinExtraActControllerTest {

    private String idToUpdate;

    @Inject
    ExtraActRepository extraActRepository;

    @BeforeEach
    void setUp() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();

        extracurricularActivity.extracurricularActivityName = "Initial Extracurricular Activity";
        extraActRepository.insert(extracurricularActivity);

        idToUpdate = extracurricularActivity.id.toString();
    }


    @Test
    @DisplayName("Create Request to Join Activity: Basic")
    public void testBasicRequestToJoinOrg() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String userName = "person1";
        String activityId = idToUpdate;

        jsonAsMap.put("userName", userName);
        jsonAsMap.put("activityId", activityId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/request")
                .then()
                .statusCode(200);
    }
}
