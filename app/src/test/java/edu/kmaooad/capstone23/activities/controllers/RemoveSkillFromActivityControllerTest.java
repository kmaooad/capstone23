package edu.kmaooad.capstone23.activities.controllers;


import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.commands.DeleteSkill;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.competences.handlers.CreateSkillHandler;
import edu.kmaooad.capstone23.competences.handlers.DeleteSkillHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RemoveSkillFromActivityControllerTest {

    @Inject
    private ExtracurricularActivityRepository activityRepository;

    @Inject
    CreateSkillHandler addSkillHandler;

    @Inject
    DeleteSkillHandler deleteSkillHandler;

    private ObjectId activityId;
    private ObjectId skillId;

    @BeforeEach
    void setUp() {
        if (skillId != null) {
            DeleteSkill deleteSkill = new DeleteSkill();
            deleteSkill.setId(skillId);
            deleteSkillHandler.handle(deleteSkill);
        }

        CreateSkill createSkill = new CreateSkill();
        createSkill.setSkillName("Sociable");
        SkillCreated skillCreated = addSkillHandler.handle(createSkill).getValue();

        skillId = skillCreated.getSkill();

        ExtracurricularActivity activity = new ExtracurricularActivity();
        activity.extracurricularActivityName = "SoftSkills";
        activity.skillIds = new ArrayList<>();
        activity.skillIds.add(skillId);
        activityRepository.insert(activity);
        activityId = activity.id;
    }

    @Test
    @DisplayName("Remove skill from activity: Basic")
    public void testBasicSkillFromActivityRemoving() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillId", skillId.toString());
        jsonAsMap.put("activityId", activityId.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activity/removeskill")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Remove not valid skill from activity: Basic")
    public void testBasicNotValidSkillFromActivityRemoving() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillId", new ObjectId());
        jsonAsMap.put("activityId", activityId.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activity/removeskill")
                .then()
                .statusCode(400);
    }
}
