package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

@QuarkusTest
public class DeleteJobPrefControllerTests {

    @Inject
    CVRepository cvRepository;

    @BeforeEach
    public void setup() {
        cvRepository.deleteAll();
    }

    @AfterEach
    public void clear() {
         cvRepository.deleteAll();
    }

    @Test
    public void deleteJobPref() {
        CV cv = new CV();
        cv.dateTimeCreated = LocalDateTime.now();
        cv.status = edu.kmaooad.capstone23.cvs.dal.CV.Status.OPEN;
        cv.visibility = edu.kmaooad.capstone23.cvs.dal.CV.Visibility.VISIBLE;
        cv.preference = new JobPreference("Kyiv", "IT", JobPreference.Category.FULL_TIME);
        cvRepository.persist(cv);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("cvId", cv.id.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/preferences/delete")
                .then()
                .statusCode(200);

        assertThat(cvRepository.findById(cv.id), nullValue());

    }
}
