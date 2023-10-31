package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.RequestToJoinDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.dal.Request;
import edu.kmaooad.capstone23.departments.dal.RequestsRepository;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateRequestToJoinHandler implements CommandHandler<RequestToJoinDepartment, RequestCreated> {

    @Inject
    private DepartmentsRepository departmentsRepository;

    @Inject
    private RequestsRepository requestsRepository;

    @Inject
    EntityBanService banService;

    private final String defaultStatus = "pending";

    public Result<RequestCreated> handle(RequestToJoinDepartment command) {


        Department department = departmentsRepository.findById(command.getDepartmentId());
        if (department == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Department not found");
        }

        if (banService.findForEntity(BannedEntityType.Department, department.id).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Department is banned");
        }

        Request request = new Request();
        // TODO: validate userName once we have a user service
        request.userName = command.getUserName();
        request.departmentId = command.getDepartmentId();
        request.status = defaultStatus;

        requestsRepository.insert(request);

        RequestCreated result = new RequestCreated(request.id.toString());

        return new Result<>(result);
    }
}