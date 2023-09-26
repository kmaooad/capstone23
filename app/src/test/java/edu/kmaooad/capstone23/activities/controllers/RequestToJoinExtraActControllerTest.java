package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
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
    ExtracurricularActivityRepository extraActRepository;

    @BeforeEach
    void setUp() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();

        extracurricularActivity.extracurricularActivityName = "Initial Extracurricular Activity";
        extraActRepository.insert(extracurricularActivity);

        idToUpdate = extracurricularActivity.id.toString();
    }


    @Test
    @DisplayName("Create Request to Join Activity: Basic")
    public void testBasicRequestToJoinAct() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String userName = "person1";
        String extraActId = idToUpdate;

        jsonAsMap.put("userName", userName);
        jsonAsMap.put("extraActId", extraActId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/extracurricularActivity/request")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create Request to Join Activity: No such activity")
    public void testRequestToJoinActivityWithNonExistentId() {
        String nonexistentId = "66fbb253275c2222267b87a1";
        String userName = "person10";

        given()
                .contentType("application/json")
                .body("{\"userName\":\"" + userName + "\",\"extraActId\":\"" + nonexistentId + "\"}")
                .when()
                .post("/extracurricularActivity/request")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create Request to Join Activity: No userName")
    public void testRequestToJoinActivityWithoutUserName() {
        String extraActId = idToUpdate;

        given()
                .contentType("application/json")
                .body("{\"extraActId\":\"" + extraActId + "\"}")
                .when()
                .post("/extracurricularActivity/request")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Create Request to Join Activity: No extraActId")
    public void testRequestToJoinActivityWithoutExtraActId() {
        String userName = "person1";

        given()
                .contentType("application/json")
                .body("{\"extraActId\":\"" + extraActId + "\"}")
                .when()
                .post("/extracurricularActivity/request")
                .then()
                .statusCode(400);
    }

}