package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateExtracurricularActivityControllerTest {
    private String idToUpdate;

    @Inject
    ExtracurricularActivityRepository extracurricularActivityRepository;

    @BeforeEach
    void setUp() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();

        extracurricularActivity.extracurricularActivityName = "Football";
        extracurricularActivity.extracurricularActivityDate = new Date();
        extracurricularActivityRepository.insert(extracurricularActivity);

        idToUpdate = extracurricularActivity.id.toString();
    }


    @Test
    @DisplayName("Update Extracurricular Activity: Basic")
    public void testBasicActivityUpdate() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String newName = "Football";
        Date newDate = new Date(2323223232L);

        jsonAsMap.put("extracurricularActivityName", newName);
        jsonAsMap.put("extracurricularActivityDate", newDate);
        jsonAsMap.put("id", idToUpdate);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/extracurricularActivity/update")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("Update Extracurricular Activity: Id not found")
    public void testActivityUpdateWithIdNotFound() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String newName = "Football";
        Date newDate = new Date(2323223232L);
        String fakeId = "123456789";

        jsonAsMap.put("extracurricularActivityName", newName);
        jsonAsMap.put("extracurricularActivityDate", newDate);
        jsonAsMap.put("id", fakeId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/extracurricularActivity/update")
                .then()
                .statusCode(500);
    }

}
