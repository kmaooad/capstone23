package edu.kmaooad.capstone23.access_rules.handlers;

import edu.kmaooad.capstone23.access_rules.commands.CreateAccessRule;
import edu.kmaooad.capstone23.access_rules.commands.UpdateAccessRule;
import edu.kmaooad.capstone23.access_rules.dal.*;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleCreated;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleUpdated;
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

@QuarkusTest
public class UpdateAccessRuleHandlerTests {

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
    CommandHandler<UpdateAccessRule, AccessRuleUpdated> ruleHandler;

    @Inject
    AccessRuleRepository ruleRepository;

    private String member;
    private String department;
    private String org;
    private String group;
    private String course;
    private String rule;

    @BeforeEach
    private void prepare(){
        member = createMember();
        department = createDepartment();
        org = createOrg();
        group = createGroup();
        course = createCourse();
        rule = createRule();
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
    private String createRule(){
        AccessRule rule = new AccessRule();
        rule.fromEntityId = new ObjectId(department);
        rule.fromEntityType = AccessRuleFromEntityType.Department;
        rule.toEntityId = new ObjectId(department);
        rule.toEntityType = AccessRuleToEntityType.Department;
        rule.ruleType = AccessRuleType.Allow;
        ruleRepository.insert(rule);
        return rule.id.toString();
    }

    private Result<AccessRuleUpdated> updateAccessRule(String fromId, AccessRuleFromEntityType fromType, String toId, AccessRuleToEntityType toType){
        UpdateAccessRule command = new UpdateAccessRule();
        command.setId(rule);
        command.setFromEntityId(member);
        command.setFromEntityType(AccessRuleFromEntityType.Member.toString());
        command.setToEntityId(department);
        command.setToEntityType(AccessRuleToEntityType.Department.toString());
        command.setRuleType("Allow");
        Result<AccessRuleUpdated> result = ruleHandler.handle(command);

        return result;
    }
    @Test
    @DisplayName("Update Access Rule: member to department")
    public void updateRuleMemberToDepartment() {
        Result<AccessRuleUpdated> result = updateAccessRule(member, AccessRuleFromEntityType.Member, department, AccessRuleToEntityType.Department);
        Assertions.assertTrue(result.isSuccess());
    }

    @Test
    @DisplayName("Update Access Rule: member to group")
    public void updateRuleMemberToGroup() {
        Result<AccessRuleUpdated> result = updateAccessRule(member, AccessRuleFromEntityType.Member, group, AccessRuleToEntityType.Group);
        Assertions.assertTrue(result.isSuccess());
    }
    @Test
    @DisplayName("Update Access Rule: member to course")
    public void updateRuleMemberToCourse() {
        Result<AccessRuleUpdated> result = updateAccessRule(member, AccessRuleFromEntityType.Member, course, AccessRuleToEntityType.Course);
        Assertions.assertTrue(result.isSuccess());
    }

    @Test
    @DisplayName("Update Access Rule: member to organisation")
    public void updateRuleMemberToOrganisation() {
        Result<AccessRuleUpdated> result = updateAccessRule(member, AccessRuleFromEntityType.Member, org, AccessRuleToEntityType.Organisation);
        Assertions.assertTrue(result.isSuccess());
    }
}
