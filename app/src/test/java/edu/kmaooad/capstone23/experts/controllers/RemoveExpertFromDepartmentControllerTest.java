package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
@QuarkusTest
public class RemoveExpertFromDepartmentControllerTest {
    private Org org;
    private Department department;

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
    @DisplayName("Remove Expert From Member: Basic")
    public void testExpertFromDepartmentRemoval() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("expertId", createTestExpert().toHexString());
        jsonAsMap.put("departmentId", department.id.toHexString());

        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/remove_expert_from_department")
                .then()
                .statusCode(200);

        // ERROR: the expert is already removed from department
        RestAssured.given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/experts/remove_expert_from_department")
                .then()
                .statusCode(400);
    }

    @AfterEach
    public void tearDown() {
        membersRepository.deleteAll();
        orgsRepository.deleteAll();
        departmentsRepository.deleteAll();
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
        member.orgId = List.of(org.id);
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
