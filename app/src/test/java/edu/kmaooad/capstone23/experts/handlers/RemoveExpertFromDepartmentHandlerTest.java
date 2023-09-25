package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromDepartment;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromMember;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromDepartment;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromMember;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

@QuarkusTest
public class RemoveExpertFromDepartmentHandlerTest {
    private Org org;
    private Department department;
    @Inject
    CommandHandler<RemoveExpertFromDepartment, ExpertRemovedFromDepartment> removeHandler;
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    DepartmentsRepository departmentsRepository;
    @Inject
    ExpertsRepository expertsRepository;
    @Inject
    MembersRepository membersRepository;

    @BeforeEach
    public void setUp() {
        org = createTestOrg();
        department = createTestDepartment();
    }

    @Test
    @DisplayName("Remove Expert From Department Handler: Basic")
    public void testSuccessfulHandling() {
        ObjectId expertId = createTestExpert();
        ObjectId departmentId = department.id;

        RemoveExpertFromDepartment command = new RemoveExpertFromDepartment();
        command.setExpertId(expertId);
        command.setDepartmentId(departmentId);

        Result<ExpertRemovedFromDepartment> result = removeHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(expertsRepository.findById(expertId).departments.stream().anyMatch(p -> p.id == departmentId));
    }

    @Test
    @DisplayName("Remove Expert with no department")
    public void testEmptyDepartments() {
        ObjectId expertId = createTestExpertEmptyDepartment();

        RemoveExpertFromDepartment command = new RemoveExpertFromDepartment();
        command.setExpertId(expertId);
        command.setDepartmentId(department.id);

        Result<ExpertRemovedFromDepartment> result = removeHandler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), ErrorCode.NOT_FOUND);
    }

    private Org createTestOrg() {
        Org org = new Org();
        org.name = "Brovary Club";
        org.industry = "Some random industry";
        org.website = "Some random website";
        orgsRepository.insert(org);
        return org;
    }

    private Department createTestDepartment() {
        Department department = new Department();
        department.name = "What is that";
        department.description = "Some descr";
        department.parent = org.name;

        departmentsRepository.insert(department);

        return department;
    }

    private ObjectId createTestMember() {
        Member member = new Member();
        member.firstName = "Test";
        member.lastName = "Member";
        member.email = randomEmail();
        member.orgId = org.id;
        membersRepository.insert(member);

        return member.id;
    }
    
    private ObjectId createTestExpert() {
        Expert expert = new Expert();
        expert.name = "Test Name";
        expert.org = org;
        expert.memberId = createTestMember();
        ArrayList<Department> departments = new ArrayList<>();
        departments.add(department);

        expert.departments = departments;
        expertsRepository.insert(expert);

        return expert.id;
    }

    private ObjectId createTestExpertEmptyDepartment() {
        Expert expert = new Expert();
        expert.name = "Test Name";
        expert.org = org;
        expert.memberId = createTestMember();
        expert.departments = new ArrayList<>();

        expertsRepository.insert(expert);

        return expert.id;
    }

    private String randomEmail() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString + "@mail.com";
    }
}
