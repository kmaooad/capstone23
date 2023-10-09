package edu.kmaooad.capstone23.group_templates.controllers;

import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteGroupTemplateControllerTests {
    @Inject
    GroupTemplatesRepository groupTemplatesRepository;
    @Inject
    GroupsRepository groupsRepository;
    @Test
    @DisplayName("Delete Group Template: Basic")
    public void testGroupTemplateDelete() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",groupTemplate.id.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/grouptemplates/delete")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete Group Template: Group Template Does Not Exist")
    public void testDeleteNonExistentGroupTemplate() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",new ObjectId().toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/grouptemplates/delete")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("Delete Group Template: Bad ID")
    public void testDeleteGroupTemplateWithWrongID() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id","badID");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/grouptemplates/delete")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("Delete Group Template: With Children")
    public void testDeleteGroupTemplateWithChildren() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Group group = new Group();
        group.name = "test_group";
        group.templateId = groupTemplate.id.toString();

        groupsRepository.insert(group);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",groupTemplate.id.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/grouptemplates/delete")
                .then()
                .statusCode(400);
    }
}
