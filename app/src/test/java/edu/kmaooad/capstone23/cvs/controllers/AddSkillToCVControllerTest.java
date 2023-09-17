package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
class AddSkillToCVControllerTest {

    @Inject
    CVRepository cvRepository;

    @Test
    @DisplayName("Update CV: valid cv value")
    public void simpleRunTest() {
        CV cv = new CV();
        cv.dateTimeCreated = LocalDateTime.now();
        cv.status = edu.kmaooad.capstone23.cvs.dal.CV.Status.OPEN;
        cv.visibility = edu.kmaooad.capstone23.cvs.dal.CV.Visibility.VISIBLE;
        cv.textInfo = "some info in cv";
        cvRepository.persist(cv);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("cvId", cv.id.toString());
        jsonAsMap.put("skillId", cv.id.toString()); // don't need to validate this stuff yet

        given().contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/update/add-skill")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Update CV: invalid cv value")
    public void simpleErrorRunTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("cvId", "some crazy stuff");
        jsonAsMap.put("skillId", "some crazy stuff"); // do not validate this stuff

        given().contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/update/add-skill")
                .then()
                .statusCode(400);
    }
}