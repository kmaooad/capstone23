package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
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
public class AddSkillToSkillSetControllerTest {

    @Inject
    private SkillSetRepository skillSetRepository;
    @Inject
    private MongoSkillsRepository skillsRepository;

    private ObjectId skillSetId;
    private ObjectId skillId;

    @BeforeEach
    void setUp() {
        SkillSet skillSet = new SkillSet();
        skillSet.name = "SoftSkills";
        skillSetRepository.insert(skillSet);
        skillSetId = skillSet.id;

        Skill skill = new Skill();
        skill.name = "Sociable";
        skillsRepository.insert(skill);
        skillId = skill.id;
    }

    @Test
    @DisplayName("Add skill to skillSet: Basic")
    public void testBasicSkillToSkillSetAddition() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillId", skillId.toString());
        jsonAsMap.put("skillSetId", skillSetId.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/addskill")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Add not valid skill to skillSet: Basic")
    public void testBasicNotValidSkillToSkillSetAddition() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("skillId", new ObjectId());
        jsonAsMap.put("skillSetId", skillSetId.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/skillset/addskill")
                .then()
                .statusCode(400);
    }
}
