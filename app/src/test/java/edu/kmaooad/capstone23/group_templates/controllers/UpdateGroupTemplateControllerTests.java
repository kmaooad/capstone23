package edu.kmaooad.capstone23.group_templates.controllers;

import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


@QuarkusTest
public class UpdateGroupTemplateControllerTests {

    @Inject
    GroupTemplatesRepository groupTemplatesRepository;
    @Test
    @DisplayName("Update Group Template: Basic")
    public void testGroupTemplateNameUpdate() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id",groupTemplate.id.toString());
        jsonAsMap.put("groupTemplateName", "RenamedGroupTemplate");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/grouptemplates/update")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", notNullValue());
    }
    @Test
    @DisplayName("Update Group Template: Invalid Name")
    public void testUpdateGroupTemplateForInvalidName() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplate.name = "GroupTemplate";
        groupTemplatesRepository.insert(groupTemplate);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", groupTemplate.id.toString());
        jsonAsMap.put("groupTemplateName", "a");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/grouptemplates/update")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("Update Group Template: Group Template Does Not Exist")
    public void testUpdateNonExistentGroupTemplate() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "badId");
        jsonAsMap.put("groupTemplateName", "RenamedGroupTemplate");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/grouptemplates/update")
                .then()
                .statusCode(400);
    }

}
