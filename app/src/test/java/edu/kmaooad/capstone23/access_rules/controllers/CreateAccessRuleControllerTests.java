package edu.kmaooad.capstone23.access_rules.controllers;

import edu.kmaooad.capstone23.access_rules.commands.CreateAccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleCreated;
import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateAccessRuleControllerTests {

    @Inject
    CommandHandler<CreateOrg, OrgCreated> createOrgHandler;

    @Inject
    CommandHandler<CreateDepartment, DepartmentCreated> createDepartmentHandler;

    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> createMemberHandler;

    @Inject
    CommandHandler<CreateGroupTemplate, GroupTemplateCreated> templateHandler;

    @Inject
    CommandHandler<CreateGroup, GroupCreated> groupHandler;

    @Inject
    CommandHandler<CreateCourse, CourseCreated> courseHandler;

    @Inject
    CommandHandler<CreateAccessRule, AccessRuleCreated> ruleHandler;

    private String member;
    private String department;
    private String org;
    private String group;
    private String course;
    @BeforeEach
    private void prepare(){
        member = createMember();
        department = createDepartment();
        org = createOrg();
        group = createGroup();
        course = createCourse();
    }

    @Test
    @DisplayName("Create Access Rule: member to department")
    public void createRuleMemberToDepartment() {
        addAccessRule(member, AccessRuleFromEntityType.Member, department, AccessRuleToEntityType.Department,200);
    }

    @Test
    @DisplayName("Create Access Rule: member to group")
    public void createRuleMemberToGroup() {
        addAccessRule(member, AccessRuleFromEntityType.Member, group, AccessRuleToEntityType.Group,200);
    }
    @Test
    @DisplayName("Create Access Rule: member to course")
    public void createRuleMemberToCourse() {
        addAccessRule(member, AccessRuleFromEntityType.Member, course, AccessRuleToEntityType.Course,200);
    }

    @Test
    @DisplayName("Create Access Rule: member to organisation")
    public void createRuleMemberToOrganisation() {
        addAccessRule(member, AccessRuleFromEntityType.Member, org, AccessRuleToEntityType.Organisation,200);
    }

    @Test
    @DisplayName("Create Access Rule: department to organisation")
    public void createRuleDepartmentToOrganisation() {
        addAccessRule(department, AccessRuleFromEntityType.Department, org, AccessRuleToEntityType.Organisation,200);
    }

    @Test
    @DisplayName("Create Access Rule: department to department")
    public void createRuleDepartmentToDepartment() {
        addAccessRule(department, AccessRuleFromEntityType.Department, createDepartment(), AccessRuleToEntityType.Department,200);
    }

    @Test
    @DisplayName("Create Access Rule: department to group")
    public void createRuleDepartmentToGroup() {
        addAccessRule(department, AccessRuleFromEntityType.Department, group, AccessRuleToEntityType.Group,200);
    }

    @Test
    @DisplayName("Create Access Rule: department to course")
    public void createRuleDepartmentToCourse() {
        addAccessRule(department, AccessRuleFromEntityType.Department, course, AccessRuleToEntityType.Course,200);
    }
    @Test
    @DisplayName("Create Access Rule: organisation to course")
    public void createRuleOrganisationToCourse() {
        addAccessRule(org, AccessRuleFromEntityType.Organisation, course, AccessRuleToEntityType.Course,200);
    }
    @Test
    @DisplayName("Create Access Rule: organisation to organisation")
    public void createRuleOrganisationToOrganisation() {
        addAccessRule(org, AccessRuleFromEntityType.Organisation, createOrg(), AccessRuleToEntityType.Organisation,200);
    }
    @Test
    @DisplayName("Create Access Rule: organisation to group")
    public void createRuleOrganisationToGroup() {
        addAccessRule(org, AccessRuleFromEntityType.Organisation, group, AccessRuleToEntityType.Group,200);
    }
    @Test
    @DisplayName("Create Access Rule: organisation to department")
    public void createRuleOrganisationToDepartment() {
        addAccessRule(org, AccessRuleFromEntityType.Organisation, department, AccessRuleToEntityType.Department,200);
    }

    @Test
    @DisplayName("Create Access Rule: from non existent")
    public void createRuleMemberFromNonExistent() {
        addAccessRule(new ObjectId().toString(), AccessRuleFromEntityType.Member, org, AccessRuleToEntityType.Organisation, 400);
    }

    @Test
    @DisplayName("Create Access Rule: from invalid Id")
    public void createRuleMemberFromInvalidId() {
        addAccessRule("1234abc", AccessRuleFromEntityType.Member, org, AccessRuleToEntityType.Organisation, 400);
    }
  
    @Test
    @DisplayName("Create Access Rule: to non existent")
    public void createRuleMemberToNonExistent() {
        addAccessRule(member, AccessRuleFromEntityType.Member, new ObjectId().toString(), AccessRuleToEntityType.Organisation, 400);
    }

    @Test
    @DisplayName("Create Access Rule: to invalid Id")
    public void createRuleMemberToInvalidId() {
        addAccessRule(member, AccessRuleFromEntityType.Member, "1234abc", AccessRuleToEntityType.Organisation, 400);
    }

    private String createMember(){
        CreateBasicMember command = new CreateBasicMember();
        command.setOrgId(new ObjectId(createOrg()));
        command.setFirstName("John");
        command.setLastName("Doe");
        String id = new ObjectId().toString();
        command.setEmail(id.substring(id.length()-5) +"@gmail.com");

        Result<BasicMemberCreated> result = createMemberHandler.handle(command);

        return result.getValue().getMemberId();
    }
    private void addAccessRule(String fromId, AccessRuleFromEntityType fromType, String toId, AccessRuleToEntityType toType, int expectedResult){
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("ruleType", "Allow" );
        jsonAsMap.put("fromEntityId", fromId );
        jsonAsMap.put("fromEntityType", fromType );
        jsonAsMap.put("toEntityId", toId );
        jsonAsMap.put("toEntityType", toType );

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/accessrules/create")
                .then()
                .statusCode(expectedResult);
    }

    private String createDepartment(){
        createOrg();
        CreateDepartment command = new CreateDepartment();
        command.setParent("NaUKMA");
        command.setName("department");
        command.setDescription("description");

        Result<DepartmentCreated> result = createDepartmentHandler.handle(command);

        return result.getValue().getId();
    }

    private String createOrg(){
        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA");
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";
        Result<OrgCreated> result = createOrgHandler.handle(command);
        return result.getValue().getOrgId();
    }

    private String createGroup(){
        CreateGroupTemplate templateCommand = new CreateGroupTemplate();
        templateCommand.setGroupTemplateName("template");
        Result<GroupTemplateCreated> resultForTemplate = templateHandler.handle(templateCommand);

        CreateGroup groupCommand = new CreateGroup();
        groupCommand.setGroupName("group");
        groupCommand.setTemplateId(resultForTemplate.getValue().getGroupTemplateId().toString());
        Result<GroupCreated> resultForGroup = groupHandler.handle(groupCommand);

        return resultForGroup.getValue().getGroupId();
    }
    private String createCourse(){
        CreateCourse createCourse = new CreateCourse();
        createCourse.setName("course");
        Result<CourseCreated> result = courseHandler.handle(createCourse);
        return result.getValue().getId();
    }
}
