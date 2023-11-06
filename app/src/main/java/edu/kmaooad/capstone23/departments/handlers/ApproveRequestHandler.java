package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.ApproveJoinRequest;
import edu.kmaooad.capstone23.departments.dal.*;
import edu.kmaooad.capstone23.departments.events.RequestApproved;
import edu.kmaooad.capstone23.departments.services.DepartmentRequestService;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped
public class ApproveRequestHandler  implements CommandHandler<ApproveJoinRequest, RequestApproved> {
    @Inject
    private DepartmentService departmentsService;

    @Inject
    private DepartmentRequestService departmentRequestService;
    @Inject
    private EntityBanService banService;

    private final String memberRole = "member";


    public Result<RequestApproved> handle(ApproveJoinRequest command) {
        // TODO: check if the user is authorized to approve the request (only department admins can approve requests)

        String requestId = command.getRequestId();
        Request request = departmentRequestService.approveRequest(requestId);

        Department department = departmentsService.getDepartmentById(request.departmentId);
        if (department == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Department not found");
        }

        if (banService.findForEntity(BannedEntityType.Department, department.id).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Department is banned");
        }

        String role = memberRole;
        Member member = new Member();
        member.userName = request.userName;
        member.role = role;

        department.members.add(member);
        departmentsService.updateDepartment(department);

        RequestApproved result = new RequestApproved(requestId);

        return new Result<>(result);
    }
}
