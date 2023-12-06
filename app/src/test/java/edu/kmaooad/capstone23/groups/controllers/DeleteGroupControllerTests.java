package edu.kmaooad.capstone23.groups.controllers;

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
public class DeleteGroupControllerTests {

    @Inject
    GroupTemplatesRepository groupTemplatesRepository;
    @Inject
    GroupsRepository groupsRepository;

    @Test
    @DisplayName("Delete Group : Basic")
    public void testGroupDelete() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Group group = new Group();
        group.name = "test_group";
        group.templateId = groupTemplate.id.toString();

        groupsRepository.insert(group);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",group.id.toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/delete")
                .then()
                .statusCode(200);
    }
    @Test
    @DisplayName("Delete Group : Group Does Not Exist")
    public void testDeleteNonExistentGroup() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",new ObjectId().toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/delete")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("Delete Group : Bad ID")
    void testDeletingGroupWithWrongID() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id","badID");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/delete")
                .then()
                .statusCode(400);
    }
}
