package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.RequestToJoinDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.Request;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import edu.kmaooad.capstone23.departments.services.DepartmentRequestService;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateRequestToJoinHandler implements CommandHandler<RequestToJoinDepartment, RequestCreated> {

    @Inject
    private DepartmentService departmentsService;

    @Inject
    private DepartmentRequestService departmentRequestService;

    @Inject
    EntityBanService banService;

    public Result<RequestCreated> handle(RequestToJoinDepartment command) {


        Department department = departmentsService.getDepartmentById(command.getDepartmentId());
        if (department == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Department not found");
        }

        if (banService.findForEntity(BannedEntityType.Department, department.id).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Department is banned");
        }

        Request request = departmentRequestService.createRequest(command.getUserName(), command.getDepartmentId());

        RequestCreated result = new RequestCreated(request.id.toString());

        return new Result<>(result);
    }
}