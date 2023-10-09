package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.SetMemberRole;
import edu.kmaooad.capstone23.departments.dal.*;
import edu.kmaooad.capstone23.departments.events.MemberRoleSetted;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@QuarkusTest
public class SetMemberRoleHandlerTest {

    @Inject
    SetMemberRoleHandler handler;

    @Inject
    CommandHandler<BanEntity, EntityBanned> banHandler;

    @Inject
    DepartmentsRepository departmentsRepository;
    private String departmentId;

    private String userName;

    @BeforeEach
    void setUp() {
        Department department = new Department();

        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        department.members = new ArrayList<>();

        Member member = new Member();
        member.userName = "Initial Member";
        member.role = "Initial Role";
        department.members.add(member);
        departmentsRepository.insert(department);

        departmentId = department.id.toString();
        userName = member.userName;
    }


    @Test
    @DisplayName("SetMemberRoleHandler basic success")
    public void testBasicSetMemberRole() {
        String departmentId = this.departmentId;
        String userName = this.userName;
        String role = "New Role";

        SetMemberRole command = new SetMemberRole();
        command.setDepartmentId(departmentId);
        command.setUserName(userName);
        command.setRole(role);

        Result<MemberRoleSetted> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());

        Assertions.assertEquals(departmentId, result.getValue().getDepartmentId());
        Assertions.assertEquals(userName, result.getValue().getUserName());
        Assertions.assertEquals(role, result.getValue().getRole());

        Department department = departmentsRepository.findById(departmentId);
        Assertions.assertNotNull(department);

        Member member = department.members.stream().filter(m -> m.userName.equals(userName)).findFirst().orElse(null);
        Assertions.assertNotNull(member);

        Assertions.assertEquals(role, member.role);
    }

    @Test
    @DisplayName("SetMemberRoleHandler department not found")
    public void testSetMemberRoleWithNonExistentDepartmentId() {
        String nonexistentDepartmentId = "64fbb243275c1111167b87a3";
        String userName = this.userName;
        String role = "New Role";

        SetMemberRole command = new SetMemberRole();
        command.setDepartmentId(nonexistentDepartmentId);
        command.setUserName(userName);
        command.setRole(role);

        Result<MemberRoleSetted> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("SetMemberRoleHandler member not found")
    public void testSetMemberRoleWithNonExistentMember() {
        String departmentId = this.departmentId;
        String nonexistentUserName = "Not Found";
        String role = "New Role";

        SetMemberRole command = new SetMemberRole();
        command.setDepartmentId(departmentId);
        command.setUserName(nonexistentUserName);
        command.setRole(role);

        Result<MemberRoleSetted> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("SetMemberRoleHandler department banned")
    public void testSetMemberRoleOnBannedDepartment() {
        String departmentId = this.departmentId;
        String nonexistentUserName = "Wannabe banned";
        String role = "New Role";

        BanEntity banRequest = new BanEntity();
        banRequest.setEntityType(BannedEntityType.Department.name());
        banRequest.setReason("Hello there");
        banRequest.setEntityId(new ObjectId(departmentId));
        var banResult = banHandler.handle(banRequest);
        Assertions.assertTrue(banResult.isSuccess());

        SetMemberRole command = new SetMemberRole();
        command.setDepartmentId(departmentId);
        command.setUserName(nonexistentUserName);
        command.setRole(role);

        Result<MemberRoleSetted> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertNotNull(result.getMessage());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.EXCEPTION);
        Assertions.assertEquals(result.getMessage(), "Department is banned");
    }
}
