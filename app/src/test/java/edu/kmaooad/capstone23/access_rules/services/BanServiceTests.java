//package edu.kmaooad.capstone23.access_rules.services;
//
//import edu.kmaooad.capstone23.access_rules.commands.CreateAccessRule;
//import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
//import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
//import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;
//import edu.kmaooad.capstone23.access_rules.events.AccessRuleCreated;
//import edu.kmaooad.capstone23.access_rules.events.EntityBanned;
//import edu.kmaooad.capstone23.activities.commands.CreateCourse;
//import edu.kmaooad.capstone23.activities.events.CourseCreated;
//import edu.kmaooad.capstone23.access_rules.dal.AccessRuleRepository;
//import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
//import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
//import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
//import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
//import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
//import edu.kmaooad.capstone23.orgs.events.OrgCreated;
//import edu.kmaooad.capstone23.common.CommandHandler;
//import edu.kmaooad.capstone23.common.Result;
//
//import io.quarkus.test.junit.QuarkusTest;
//import jakarta.inject.Inject;
//
//import org.bson.types.ObjectId;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//@QuarkusTest
//public class BanServiceTests {
//
//    @Inject
//    BanService banService;
//
//    @Inject
//    CommandHandler<CreateCourse, CourseCreated> courseHandler;
//
//    @Inject
//    CommandHandler<CreateOrg, OrgCreated> createOrgHandler;
//
//    @Inject
//    CommandHandler<CreateBasicMember, BasicMemberCreated> createMemberHandler;
//
//    @Inject
//    CommandHandler<CreateDepartment, DepartmentCreated> createDepartmentHandler;
//
//    @Inject
//    CommandHandler<CreateAccessRule, AccessRuleCreated> ruleHandler;
//
//    @Inject
//    AccessRuleRepository accessRuleRepository;
//
//    private ObjectId memberId;
//    private ObjectId courseId;
//    private ObjectId deptId;
//    private ObjectId deptId2;
//    private ObjectId orgId;
//
//    @BeforeEach
//    public void setup() {
//
//        memberId = createMember();
//        courseId = createCourse();
//        deptId = createDepartment();
//        deptId2 = createDepartment();
//        orgId = createOrg();
//        addAccessRule(AccessRuleFromEntityType.Member, memberId, AccessRuleToEntityType.Course, courseId);
//        addAccessRule(AccessRuleFromEntityType.Member, memberId, AccessRuleToEntityType.Department, deptId);
//        addAccessRule(AccessRuleFromEntityType.Department, deptId2, AccessRuleToEntityType.Course, courseId);
//        addAccessRule(AccessRuleFromEntityType.Organisation, orgId, AccessRuleToEntityType.Course, courseId);
//    }
//
//
//    @Test
//    @DisplayName("Ban Existing Member")
//    public void banExistingMember() {
//
//        Result<EntityBanned> result = banService.banEntity(memberId, AccessRuleFromEntityType.Member);
//        Assertions.assertTrue(result.isSuccess());
//        List<AccessRule> accessRules = accessRuleRepository.findByEntityIdAndType(memberId, AccessRuleFromEntityType.Member);
//
//        for (AccessRule rule : accessRules) {
//            Assertions.assertTrue(rule.banned);
//        }
//    }
//
//    @Test
//    @DisplayName("Ban Existing Department")
//    public void banExistingDepartment() {
//
//        Result<EntityBanned> result = banService.banEntity(deptId, AccessRuleFromEntityType.Department);
//        Assertions.assertTrue(result.isSuccess());
//        List<AccessRule> accessRules = accessRuleRepository.findByEntityIdAndType(deptId, AccessRuleFromEntityType.Department);
//
//        for (AccessRule rule : accessRules) {
//            Assertions.assertTrue(rule.banned);
//        }
//    }
//
//    @Test
//    @DisplayName("Ban Existing Organisation")
//    public void banExistingOrganisation() {
//
//        Result<EntityBanned> result = banService.banEntity(orgId, AccessRuleFromEntityType.Organisation);
//        Assertions.assertTrue(result.isSuccess());
//        List<AccessRule> accessRules = accessRuleRepository.findByEntityIdAndType(orgId, AccessRuleFromEntityType.Organisation);
//
//        for (AccessRule rule : accessRules) {
//            Assertions.assertTrue(rule.banned);
//        }
//    }
//
//
//    private ObjectId createMember(){
//        CreateBasicMember command = new CreateBasicMember();
//        command.setOrgId(createOrg());
//        command.setFirstName("John");
//        command.setLastName("Doe");
//        String id = new ObjectId().toString();
//        command.setEmail(id.substring(id.length()-5) +"@gmail.com");
//
//        Result<BasicMemberCreated> result = createMemberHandler.handle(command);
//
//        return new ObjectId(result.getValue().getMemberId());
//    }
//
//    private ObjectId createOrg(){
//        CreateOrg command = new CreateOrg();
//        command.setOrgName("NaUKMA");
//        command.industry = "Education";
//        command.website = "https://www.ukma.edu.ua/eng/";
//        Result<OrgCreated> result = createOrgHandler.handle(command);
//        return new ObjectId(result.getValue().getOrgId());
//    }
//
//    private ObjectId createCourse(){
//        CreateCourse createCourse = new CreateCourse();
//        createCourse.setName("course");
//        Result<CourseCreated> result = courseHandler.handle(createCourse);
//        return new ObjectId(result.getValue().getId());
//    }
//
//    private ObjectId createDepartment(){
//        createOrg();
//        CreateDepartment command = new CreateDepartment();
//        command.setParent("NaUKMA");
//        command.setName("department");
//        command.setDescription("description");
//
//        Result<DepartmentCreated> result = createDepartmentHandler.handle(command);
//
//        return new ObjectId(result.getValue().getId());
//    }
//
//    private void addAccessRule(AccessRuleFromEntityType fromType, ObjectId fromId, AccessRuleToEntityType toType, ObjectId toId) {
//        CreateAccessRule command = new CreateAccessRule();
//        command.setFromEntityId(fromId);
//        command.setFromEntityType(fromType.toString());
//        command.setToEntityId(toId);
//        command.setToEntityType(toType.toString());
//        command.setRuleType("Allow");
//
//        ruleHandler.handle(command);
//
//    }
//
//
//}
