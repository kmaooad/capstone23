package edu.kmaooad.capstone23.groups.controllers;

import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;

import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import edu.kmaooad.capstone23.groups.commands.AssignGroupToActivity;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.events.ActivityAssigned;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.commands.RelateJobToActivities;
import edu.kmaooad.capstone23.jobs.events.ActivityRelated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
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
public class UnassignGroupToActivityControllerTests {
    @Inject
    CommandHandler<CreateGroup, GroupCreated> createGroupHandler;
    @Inject
    CommandHandler<AssignGroupToActivity, ActivityAssigned> handlerForRelation;
    @Inject
    CommandHandler<CreateCourse, CourseCreated> handlerForActivities;
    @Inject
    CommandHandler<CreateGroupTemplate, GroupTemplateCreated> templateHandler;

    private ObjectId idToUpdate;


    @Inject
    CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        Course course = new Course();
        course.name = "Initial Course";
        courseRepository.insert(course);
        idToUpdate = course.id;

    }

    @Test
    @DisplayName("Delete Relate Job To Activities: existed relation, valid input")
    public void testBasicJobActivitiesConnectionRemoving() {

        CreateGroup command = new CreateGroup();
        command.setGroupName("se");
        CreateGroupTemplate templateCommand = new CreateGroupTemplate();
        templateCommand.setGroupTemplateName("template");
        Result<GroupTemplateCreated> resultForTemplate = templateHandler.handle(templateCommand);
        command.setTemplateId(resultForTemplate.getValue().getGroupTemplateId().toString());

        Result<GroupCreated> result = createGroupHandler.handle(command);

        CreateCourse commandCourse = new CreateCourse();
        commandCourse.setName("Math");
        Result<CourseCreated> resultCourse = handlerForActivities.handle(commandCourse);

        AssignGroupToActivity commandAssignGroupToActivities = new AssignGroupToActivity();

        ObjectId id = new ObjectId(result.getValue().getGroupId());
        commandAssignGroupToActivities.setGroupId(id);
        ObjectId idCourse = new ObjectId(resultCourse.getValue().getId());
        commandAssignGroupToActivities.setActivityId(idCourse);
        Result<ActivityAssigned> resultAssignGroupToActivities = handlerForRelation.handle(commandAssignGroupToActivities);

        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("groupId", resultAssignGroupToActivities.getValue().getGroupId().toHexString());
        jsonAsMap.put("activityId", resultAssignGroupToActivities.getValue().getActivitiesId().toHexString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/unassign_group_to_activities")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Relate Group To Activities: notExisted group")
    public void testNotExistedGroupActivityConnectionCreation() {
        ObjectId id = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");

        CreateCourse commandCourse = new CreateCourse();
        commandCourse.setName("Math");
        Result<CourseCreated> resultCourse = handlerForActivities.handle(commandCourse);
        ObjectId idCourse = new ObjectId(resultCourse.getValue().getId());

        Map<String, Object> jsonAsMap = new HashMap<>();

        jsonAsMap.put("groupId", id);
        jsonAsMap.put("activityId", idCourse);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/groups/unassign_group_to_activities")
                .then()
                .statusCode(400);
    }


}
