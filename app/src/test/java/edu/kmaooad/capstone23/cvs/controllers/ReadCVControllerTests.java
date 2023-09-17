package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class ReadCVControllerTests {

    @Inject
    CVRepository cvRepository;

    @BeforeEach
    public void setup() {
        cvRepository.deleteAll();
        CV cv = new CV();
        cv.dateTimeCreated = LocalDateTime.now();
        cv.status = edu.kmaooad.capstone23.cvs.dal.CV.Status.OPEN;
        cv.visibility = edu.kmaooad.capstone23.cvs.dal.CV.Visibility.VISIBLE;
        cvRepository.persist(cv);
    }

    @AfterEach
    public void clear(){
        cvRepository.deleteAll();
    }

    @Test
    @DisplayName("Read CV: no params")
    public void readCV() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/read")
                .then()
                .statusCode(200)
                .assertThat()
                .body("cvs", notNullValue());
    }

    @Test
    @DisplayName("Read CV: status param")
    public void readParamCV() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("status", CV.Status.CLOSED);
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/read")
                .then()
                .statusCode(200)
                .assertThat()
                .body("cvs", empty());
    }
}
