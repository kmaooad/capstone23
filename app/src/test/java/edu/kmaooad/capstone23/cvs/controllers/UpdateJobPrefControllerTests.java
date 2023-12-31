package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UpdateJobPrefControllerTests {

    @Inject
    CVRepository cvRepository;

    @Test
    @DisplayName("Update JobPref: valid values")
    public void updateJobPref_AndGetStatus200(){
       CV cv = provideCVWithPref();

        Map<String, Object> pref = new HashMap<>();
        pref.put("cvId", cv.id.toString());
        pref.put("category", JobPreference.Category.FULL_TIME);

        given().contentType("application/json")
                .body(pref)
                .when()
                .post("/cvs/preferences/update")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Update JobPref: invalid values")
    public void updateInvalidJobPref_AndGetError(){
        Map<String, Object> prefNoId = new HashMap<>();
        prefNoId.put("category", JobPreference.Category.FULL_TIME);

        given().contentType("application/json")
                .body(prefNoId)
                .when()
                .post("/cvs/preferences/update")
                .then()
                .statusCode(400);
    }

    private CV provideCVWithPref() {
        CV cv = new CV();
        cv.dateTimeCreated = LocalDateTime.now();
        cv.status = edu.kmaooad.capstone23.cvs.dal.CV.Status.OPEN;
        cv.visibility = edu.kmaooad.capstone23.cvs.dal.CV.Visibility.VISIBLE;
        cv.preference = new JobPreference("Kyiv", "IT", JobPreference.Category.FULL_TIME);
        cvRepository.persist(cv);
        return cv;
    }
}
