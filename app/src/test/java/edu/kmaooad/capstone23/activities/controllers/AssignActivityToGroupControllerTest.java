package edu.kmaooad.capstone23.activities.controllers;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplate;
import edu.kmaooad.capstone23.group_templates.dal.GroupTemplatesRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class AssignActivityToGroupControllerTest {
    private ObjectId groupId;
    private ObjectId activityId;

    @Inject
    GroupTemplatesRepository groupTemplatesRepository;

    @BeforeEach
    void setup() {
        GroupTemplate groupTemplate = new GroupTemplate();
        groupTemplatesRepository.insert(groupTemplate);
        groupId = groupTemplate.id;
    }

    @Test
    @DisplayName("Assign activity to a non-existing group")
    public void testAssignActivityToGroup() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", "nonExistentId");
        jsonAsMap.put("activityName", "iOS activity");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/activities/assign/student")
                .then()
                .statusCode(500);
    }
}
