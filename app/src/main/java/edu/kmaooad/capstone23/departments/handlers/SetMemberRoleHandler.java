package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.SetMemberRole;
import edu.kmaooad.capstone23.departments.dal.*;
import edu.kmaooad.capstone23.departments.events.MemberRoleSetted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SetMemberRoleHandler implements CommandHandler<SetMemberRole, MemberRoleSetted> {
    @Inject
    private DepartmentsRepository departmentsRepository;

    @Inject
    EntityBanService banService;

    public Result<MemberRoleSetted> handle(SetMemberRole command) {
        // TODO: check if the user is authorized to approve the request (only department admins can approve requests)

        String departmentId = command.getDepartmentId();
        String userName = command.getUserName();
        String role = command.getRole();
        Department department = departmentsRepository.findById(departmentId);
        if (department == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Department not found");
        }

        if (banService.findForEntity(BannedEntityType.Department, department.id).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Department is banned");
        }

        Member member = department.members.stream().filter(m -> m.userName.equals(userName)).findFirst().orElse(null);

        if (member == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Member not found");
        }

        member.role = role;

        departmentsRepository.update(department);

        MemberRoleSetted result = new MemberRoleSetted(departmentId, userName, role);


        return new Result<>(result);
    }
}

