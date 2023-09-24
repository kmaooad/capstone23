package edu.kmaooad.capstone23.groups.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateGroupControllerTests {

    @Inject
    CommandHandler<CreateGroupTemplate, GroupTemplateCreated> handler;
    @Test
    @DisplayName("Create Group : Basic")
    public void testBasicGroupCreation() {
        CreateGroupTemplate command = new CreateGroupTemplate();
        command.setGroupTemplateName("testGroupTemplate");

        Result<GroupTemplateCreated> result = handler.handle(command);
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("groupName", "test_name" );
        jsonAsMap.put("templateId", result.getValue().getGroupTemplateId().toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/create")
                .then()
                .statusCode(200);
    }
    @Test
    @DisplayName("Create Group : Name Validation")
    public void testGroupCreationNameValidation() {
        CreateGroupTemplate command = new CreateGroupTemplate();
        command.setGroupTemplateName("testGroupTemplate");

        Result<GroupTemplateCreated> result = handler.handle(command);
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("groupName", "" );
        jsonAsMap.put("templateId", result.getValue().getGroupTemplateId().toString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/create")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("Create Group : Template Validation")
    public void testGroupCreationValidationOfTemplate() {
        CreateGroupTemplate command = new CreateGroupTemplate();
        command.setGroupTemplateName("testGroupTemplate");

        Result<GroupTemplateCreated> result = handler.handle(command);
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("groupName", "test_name" );
        jsonAsMap.put("templateId", "123abcdef");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/create")
                .then()
                .statusCode(400);
    }

}
