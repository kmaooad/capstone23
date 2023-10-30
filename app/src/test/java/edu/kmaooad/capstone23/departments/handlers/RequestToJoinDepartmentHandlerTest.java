package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.RequestToJoinDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.Request;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class RequestToJoinDepartmentHandlerTest {
    @Inject
    CreateRequestToJoinHandler handler;

    @Inject
    CommandHandler<BanEntity, EntityBanned> banHandler;

    @Inject
    DepartmentDriver departmentDriver;

    private String departmentId;

    @BeforeEach
    void setUp() {
        Department department = departmentDriver.createDepartment();

        departmentId = department.id.toString();
    }

    @Test
    @DisplayName("Create Request to Join Department: Basic")
    public void testBasicRequestToJoinDepartment() {
        String userName = "user1";

        RequestToJoinDepartment command = new RequestToJoinDepartment();
        command.setUserName(userName);
        command.setDepartmentId(departmentId);

        Result<RequestCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        Request resultRequest = departmentDriver.findRequestById(result.getValue().getId());
        Assertions.assertNotNull(resultRequest);
    }


    @Test
    @DisplayName("Create Request to Join Department: Error handling when department is not found")
    public void testRequestToJoinDepartmentWithNonExistentDepartment() {
        String userName = "user1";
        departmentId = "64fbb243275c1111167b87a3";

        RequestToJoinDepartment command = new RequestToJoinDepartment();
        command.setUserName(userName);
        command.setDepartmentId(departmentId);

        Result<RequestCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals("Department not found", result.getMessage());
    }

    @Test
    @DisplayName("Create Request to Join Department: Error with ban on Department")
    public void testRequestToJoinDepartmentWithBan() {
        BanEntity banRequest = new BanEntity();
        banRequest.setEntityType(BannedEntityType.Department.name());
        banRequest.setReason("Hello there");
        banRequest.setEntityId(new ObjectId(departmentId));
        var banResult = banHandler.handle(banRequest);
        Assertions.assertTrue(banResult.isSuccess());

        String userName = "user1";

        RequestToJoinDepartment command = new RequestToJoinDepartment();
        command.setUserName(userName);
        command.setDepartmentId(departmentId);

        Result<RequestCreated> createResult = handler.handle(command);

        Assertions.assertFalse(createResult.isSuccess());
        Assertions.assertNotNull(createResult.getMessage());
        Assertions.assertEquals(createResult.getErrorCode(), ErrorCode.EXCEPTION);
        Assertions.assertEquals(createResult.getMessage(), "Department is banned");
    }    
}
