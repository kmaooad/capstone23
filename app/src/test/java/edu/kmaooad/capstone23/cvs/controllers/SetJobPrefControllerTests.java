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
public class SetJobPrefControllerTests {

    @Inject
    CVRepository cvRepository;

    @Test
    @DisplayName("Set Job Prefs: valid values")
    public void createAndSetValidJobPref_AndGetStatus200() {
        CV cv = provideCVWithNoPref();
        Map<String, Object> pref = new HashMap<>();
        pref.put("cvId", cv.id.toString());
        pref.put("location", "Kyiv");
        pref.put("industry", "IT");
        pref.put("category", JobPreference.Category.PART_TIME);

        given().contentType("application/json")
                .body(pref)
                .when()
                .post("/cvs/preferences")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Set Job Prefs: invalid values")
    public void createAndSetInvalidJobPref_AndGetError() {
        CV cv = provideCVWithNoPref();

        Map<String, Object> prefNoCvId = new HashMap<>();
        prefNoCvId.put("location", "Kyiv");
        prefNoCvId.put("industry", "IT");
        prefNoCvId.put("category", JobPreference.Category.PART_TIME);

        given().contentType("application/json")
                .body(prefNoCvId)
                .when()
                .post("/cvs/preferences")
                .then()
                .statusCode(400);

        Map<String, Object> prefInvalidLocation = new HashMap<>();
        prefInvalidLocation.put("cvId", cv.id);
        prefInvalidLocation.put("location", " ");
        prefInvalidLocation.put("industry", "IT");
        prefInvalidLocation.put("category", JobPreference.Category.PART_TIME);

        given().contentType("application/json")
                .body(prefInvalidLocation)
                .when()
                .post("/cvs/preferences")
                .then()
                .statusCode(400);

        Map<String, Object> prefInvalidIndustry = new HashMap<>();
        prefInvalidIndustry.put("cvId", cv.id);
        prefInvalidIndustry.put("location", "Kyiv");
        prefInvalidIndustry.put("industry", "  ");
        prefInvalidIndustry.put("category", JobPreference.Category.PART_TIME);

        given().contentType("application/json")
                .body(prefInvalidIndustry)
                .when()
                .post("/cvs/preferences")
                .then()
                .statusCode(400);
    }

    private CV provideCVWithNoPref() {
        CV cv = new CV();
        cv.dateTimeCreated = LocalDateTime.now();
        cv.status = edu.kmaooad.capstone23.cvs.dal.CV.Status.OPEN;
        cv.visibility = edu.kmaooad.capstone23.cvs.dal.CV.Visibility.VISIBLE;
        cvRepository.persist(cv);
        return cv;
    }

}
