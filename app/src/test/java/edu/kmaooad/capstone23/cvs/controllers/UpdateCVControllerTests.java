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
public class UpdateCVControllerTests {

    @Inject
    CVRepository cvRepository;

    @Test
    @DisplayName("Update CV: valid values")
    public void updateCV_AndGetStatus200(){
        CV cv = new CV();
        cv.dateTimeCreated = LocalDateTime.now();
        cv.status = edu.kmaooad.capstone23.cvs.dal.CV.Status.OPEN;
        cv.visibility = edu.kmaooad.capstone23.cvs.dal.CV.Visibility.VISIBLE;
        cvRepository.persist(cv);

        Map<String, Object> allValuesUpdate = new HashMap<>();
        allValuesUpdate.put("cvId", cv.id.toString());
        allValuesUpdate.put("status", CV.Status.CLOSED);
        allValuesUpdate.put("visibility", CV.Visibility.HIDDEN);
        allValuesUpdate.put("textInfo", "new info");

        given().contentType("application/json")
                .body(allValuesUpdate)
                .when()
                .post("/cvs/update")
                .then()
                .statusCode(200);

        Map<String, Object> noValuesUpdate = new HashMap<>();
        noValuesUpdate.put("cvId", cv.id.toString());

        given().contentType("application/json")
                .body(noValuesUpdate)
                .when()
                .post("/cvs/update")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Update CV: invalid values")
    public void updateInvalidCV_AndGetError(){
        CV cv = new CV();
        cv.dateTimeCreated = LocalDateTime.now();
        cv.status = edu.kmaooad.capstone23.cvs.dal.CV.Status.OPEN;
        cv.visibility = edu.kmaooad.capstone23.cvs.dal.CV.Visibility.VISIBLE;
        cvRepository.persist(cv);

        Map<String, Object> noIdUpdate = new HashMap<>();
        noIdUpdate.put("status", CV.Status.CLOSED);
        noIdUpdate.put("visibility", CV.Visibility.HIDDEN);
        noIdUpdate.put("textInfo", "new info");

        given().contentType("application/json")
                .body(noIdUpdate)
                .when()
                .post("/cvs/update")
                .then()
                .statusCode(400);
    }
}
