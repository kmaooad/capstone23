package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.MongoSkillsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AddSkillToActivityControllerTest {
    @Inject
    private ExtracurricularActivityRepository activityRepository;
    @Inject
    private MongoSkillsRepository skillsRepository;

    private ObjectId activityId;
    private ObjectId skillId;

    @BeforeEach
    void setUp() {
        ExtracurricularActivity activity = new ExtracurricularActivity();
        activity.extracurricularActivityName = "Web dev hackathon";
        activityRepository.insert(activity);
        activityId = activity.id;

        Skill skill = new Skill();
        skill.name = "Http protocol";
        skillsRepository.insert(skill);
        skillId = skill.id;
    }

    @Test
    @DisplayName("Add skill to activity")
    public void testBasicSkillToSkillSetAddition() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillId", skillId.toString());
        jsonAsMap.put("activityId", activityId.toString());

        given()
            .contentType("application/json")
            .body(jsonAsMap)
            .when()
            .post("/extracurricularActivity/addskill")
            .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("Add not existent skill to activity")
    public void testBasicNotValidSkillToSkillSetAddition() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillId", new ObjectId());
        jsonAsMap.put("activityId", activityId.toString());

        given()
            .contentType("application/json")
            .body(jsonAsMap)
            .when()
            .post("/extracurricularActivity/addskill")
            .then()
            .statusCode(400);
    }
}
