package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.*;

import static io.restassured.RestAssured.given;

@QuarkusTest
class AddSkillToCVControllerTest {

    @Inject
    CVRepository cvRepository;
    @Inject
    SkillsRepository skillsRepository;

    Map<String, String> validValues;
    Map<String, String> invalidValues;

    @BeforeEach
    public void initValidValues() {
        cvRepository.deleteAll();
        skillsRepository.deleteAll();
        if (validValues == null) {
            validValues = new HashMap<>();
        } else {
            validValues.clear();
        }


        CV validCv0 = new CV();
        Skill validSkill0 = new Skill();

        validCv0.status = CV.Status.OPEN;
        validCv0.dateTimeCreated = LocalDateTime.now();
        validCv0.autoAddCompetences = false;

        CV validCv1 = new CV();
        Skill validSkill1 = new Skill();

        validCv1.status = CV.Status.CLOSED;
        validCv1.dateTimeCreated = LocalDateTime.now().minusDays(2);
        validCv1.skills = new HashSet<>();
        validCv1.textInfo = "Some info";
        validCv1.visibility = CV.Visibility.VISIBLE;
        validCv1.autoAddCompetences = false;
        validSkill1.name = "C++";

        cvRepository.persist(validCv0);
        skillsRepository.insert(validSkill0);
        validValues.put(validCv0.id.toString(), validSkill0.id.toString());

        cvRepository.persist(validCv1);
        skillsRepository.insert(validSkill1);
        validValues.put(validCv1.id.toString(), validSkill1.id.toString());
    }

    @BeforeEach
    public void initInvalidValues() {
        cvRepository.deleteAll();
        skillsRepository.deleteAll();
        if (invalidValues == null) {
            invalidValues = new HashMap<>();
        } else {
            invalidValues.clear();
        }

        Skill validSkill1 = new Skill();
        skillsRepository.insert(validSkill1);

        invalidValues.put("some random string", "some random string");
        invalidValues.put("invalid cv id", validSkill1.id.toString());

    }

    @Test
    @DisplayName("Add skill to CV: single valid values run")
    public void firstRun() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("cvId", validValues.keySet().toArray()[0]);
        jsonAsMap.put("skillId", validValues.values().toArray()[0]);

        given().contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/cvs/update/add-skill")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Add skill to CV: multiple valid values")
    public void multipleValidValuesRun() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        for (Map.Entry<String, String> e : validValues.entrySet()) {
            jsonAsMap.clear();
            jsonAsMap.put("cvId", e.getKey());
            jsonAsMap.put("skillId", e.getValue());
            given().contentType("application/json")
                    .body(jsonAsMap)
                    .when()
                    .post("/cvs/update/add-skill")
                    .then()
                    .statusCode(200);
        }
    }

    @Test
    @DisplayName("Add skill to CV: invalid cv value")
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

    @Test
    @DisplayName("Add skill to CV: multiple invalid values")
    public void multipleErrorRunTest() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        for (Map.Entry<String, String> e : invalidValues.entrySet()) {
            jsonAsMap.clear();
            jsonAsMap.put("cvId", e.getKey());
            jsonAsMap.put("skillId", e.getValue());
            given().contentType("application/json")
                    .body(jsonAsMap)
                    .when()
                    .post("/cvs/update/add-skill")
                    .then()
                    .statusCode(400);
        }
    }

}