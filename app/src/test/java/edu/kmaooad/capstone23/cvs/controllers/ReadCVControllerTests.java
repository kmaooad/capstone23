package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
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
        cv.status = CV.Status.OPEN;
        cv.visibility = CV.Visibility.VISIBLE;
        cv.preference = new JobPreference("Kyiv", "Marketing", JobPreference.Category.PART_TIME);
        cvRepository.persist(cv);

        CV cv1 = new CV();
        cv1.dateTimeCreated = LocalDateTime.now();
        cv1.status = edu.kmaooad.capstone23.cvs.dal.CV.Status.OPEN;
        cv1.visibility = CV.Visibility.HIDDEN;
        cv1.preference = new JobPreference("Kyiv", "Marketing", JobPreference.Category.FULL_TIME);
        cvRepository.persist(cv1);

        CV cv2 = new CV();
        cv2.dateTimeCreated = LocalDateTime.now();
        cv2.status = CV.Status.CLOSED;
        cv2.visibility = CV.Visibility.VISIBLE;
        cvRepository.persist(cv2);

        CV cv3 = new CV();
        cv3.dateTimeCreated = LocalDateTime.now();
        cv3.status = CV.Status.CLOSED;
        cv3.visibility = CV.Visibility.VISIBLE;
        cv3.preference = new JobPreference("Kyiv", "IT", JobPreference.Category.PART_TIME);
        cvRepository.persist(cv3);

        CV cv4 = new CV();
        cv4.dateTimeCreated = LocalDateTime.now();
        cv4.status = CV.Status.CLOSED;
        cv4.visibility = CV.Visibility.VISIBLE;
        cv4.preference = new JobPreference("Dnipro", "IT", JobPreference.Category.FULL_TIME);
        cvRepository.persist(cv4);

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
                .body("cvs", hasSize(4));
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
                .body("cvs", hasSize(3));
    }

    @Test
    @DisplayName("Read CV: industry param")
    public void readCVByIndustry() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("industry", "Marketing");
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/read")
                .then()
                .statusCode(200)
                .assertThat()
                .body("cvs", hasSize(1));
    }


    @Test
    @DisplayName("Read CV: industry & location")
    public void readCVByLocation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("industry", "IT");
        jsonAsMap.put("location", "Dnipro");
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/read")
                .then()
                .statusCode(200)
                .assertThat()
                .body("cvs", hasSize(1));
    }


    @Test
    @DisplayName("Read CV: industry & category")
    public void readCVByCategory() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("industry", "IT");
        jsonAsMap.put("category", "FULL_TIME");
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/read")
                .then()
                .statusCode(200)
                .assertThat()
                .body("cvs", hasSize(1));
    }


}
