package edu.kmaooad.capstone23.groups.controllers;

import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import edu.kmaooad.capstone23.group_templates.handlers.UpdateGroupTemplateHandler;
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
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class UpdateGroupControllerTests {
    @Inject
    GroupTemplatesRepository groupTemplatesRepository;
    @Inject
    GroupsRepository groupsRepository;

    @Test
    @DisplayName("Update Group : Basic")
    public void testGroupNameUpdate() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Group group = new Group();
        group.name = "test_group";
        group.templateId = groupTemplate.id.toString();

        groupsRepository.insert(group);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",group.id.toString());
        jsonAsMap.put("groupName", "new_group");
        jsonAsMap.put("templateId",groupTemplate.id.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/update")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("templateId",notNullValue());
    }
    @Test
    @DisplayName("Update Group : Name Validation")
    public void testUpdateGroupForInvalidGroupName() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Group group = new Group();
        group.name = "test_group";
        group.templateId = groupTemplate.id.toString();

        groupsRepository.insert(group);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",group.id.toString());
        jsonAsMap.put("groupName", "");
        jsonAsMap.put("templateId",groupTemplate.id.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/update")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("Update Group : Invalid Template ID")
    public void testUpdateGroupForInvalidTemplateID() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Group group = new Group();
        group.name = "test_group";
        group.templateId = groupTemplate.id.toString();

        groupsRepository.insert(group);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",group.id.toString());
        jsonAsMap.put("groupName", "new_name");
        jsonAsMap.put("templateId","123abcdef");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/update")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("Update Group : Template ID Doew Not Exist")
    public void testUpdateGroupForNonExistentTemplateID() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Group group = new Group();
        group.name = "test_group";
        group.templateId = groupTemplate.id.toString();

        groupsRepository.insert(group);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",group.id.toString());
        jsonAsMap.put("groupName", "new_name");
        jsonAsMap.put("templateId",new ObjectId().toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/update")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("Update Group : Invalid ID")
    public void testUpdateGroupForInvalidID() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id","123abcdef");
        jsonAsMap.put("groupName", "new_name");
        jsonAsMap.put("templateId",groupTemplate.id);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/update")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("Update Group : Non Existent Group")
    public void testUpdateNonExistentGroup() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",new ObjectId().toString());
        jsonAsMap.put("groupName", "new_name");
        jsonAsMap.put("templateId",groupTemplate.id);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/update")
                .then()
                .statusCode(400);
    }
}
