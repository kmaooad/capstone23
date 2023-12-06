package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.handlers.BanEntityHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.ApproveJoinRequest;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.dal.Request;
import edu.kmaooad.capstone23.departments.dal.RequestsRepository;
import edu.kmaooad.capstone23.departments.drivers.DepartmentDriver;
import edu.kmaooad.capstone23.departments.events.RequestApproved;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.constraints.AssertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@QuarkusTest
public class ApproveRequestHandlerTest {

    @Inject
    ApproveRequestHandler handler;

    @Inject
    DepartmentService departmentService;

    @Inject
    DepartmentDriver departmentDriver;

    @Inject
    RequestsRepository requestsRepository;

    @Inject
    BanEntityHandler banEntityHandler;

    private String requestId;

    @BeforeEach
    void setUp() {
        Department department = departmentDriver.createDepartment();

        Request request = new Request();
        request.userName = "user1@ukma.edu";
        request.departmentId = department.id.toString();
        request.status = "pending";
        requestsRepository.insert(request);

        requestId = request.id.toString();
    }


    @Test
    @DisplayName("Approve Join Request: Basic")
    public void testBasicApproveJoinRequest() {
        String requestId = this.requestId;

        ApproveJoinRequest command = new ApproveJoinRequest();
        command.setRequestId(requestId);

        Result<RequestApproved> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());

        Request resultRequest = requestsRepository.findById(requestId);

        Assertions.assertNotNull(resultRequest);

        Assertions.assertEquals("approved", resultRequest.status);

        Department department = departmentService.getDepartmentById(resultRequest.departmentId);

        Assertions.assertNotNull(department);

        Assertions.assertNotNull(department.members.stream().filter(member -> member.userName.equals(resultRequest.userName)).findFirst());
    }


    @Test
    @DisplayName("Approve Join Request: Error handling when request is not found")
    public void testApproveJoinRequestWithNonExistentRequest() {
        String nonexistentId = "64fbb243275c1111167b87a3";

        ApproveJoinRequest command = new ApproveJoinRequest();
        command.setRequestId(nonexistentId);

        Result<RequestApproved> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals("Request not found", result.getMessage());
    }


    @Test
    @DisplayName("Approve Join Request: Error handling when department is banned")
    public void testApproveJoinRequesForBannedDepartment() {
        var request = requestsRepository.findById(requestId);
        var department = departmentService.getDepartmentById(request.departmentId);

        BanEntity banCommand = new BanEntity();
        banCommand.setEntityType("Department");
        banCommand.setEntityId(department.id);
        banCommand.setReason("Hello there");

        var banResult = banEntityHandler.handle(banCommand);
        Assertions.assertTrue(banResult.isSuccess());

        ApproveJoinRequest command = new ApproveJoinRequest();
        command.setRequestId(requestId);

        Result<RequestApproved> result = handler.handle(command);
        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.EXCEPTION, result.getErrorCode());
        Assertions.assertEquals("Department is banned", result.getMessage());
    }
}
