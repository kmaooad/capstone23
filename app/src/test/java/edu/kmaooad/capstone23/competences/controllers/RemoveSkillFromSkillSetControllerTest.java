package edu.kmaooad.capstone23.competences.controllers;


import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
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
public class RemoveSkillFromSkillSetControllerTest {

    @Inject
    private SkillSetRepository skillSetRepository;
    @Inject
    private SkillsRepository skillsRepository;

    private ObjectId skillSetId;
    private ObjectId skillId;

    @BeforeEach
    void setUp() {
        Skill skill = new Skill();
        skill.name = "Sociable";
        skillsRepository.insert(skill);
        skillId = skill.id;

        SkillSet skillSet = new SkillSet();
        skillSet.name = "SoftSkills";
        skillSet.skillIds = new ArrayList<>();
        skillSet.skillIds.add(skillId);
        skillSetRepository.insert(skillSet);
        skillSetId = skillSet.id;
    }

    @Test
    @DisplayName("Remove skill from skillSet: Basic")
    public void testBasicSkillFromSkillSetRemoving() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillId", skillId.toString());
        jsonAsMap.put("skillSetId", skillSetId.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/removeskill")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Remove not valid skill from skillSet: Basic")
    public void testBasicNotValidSkillFromSkillSetRemoving() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillId", new ObjectId());
        jsonAsMap.put("skillSetId", skillSetId.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/removeskill")
                .then()
                .statusCode(400);
    }
}
