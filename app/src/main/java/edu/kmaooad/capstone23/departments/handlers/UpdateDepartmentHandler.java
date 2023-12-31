package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.ban.commands.IsEntityBannedV2;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.service.EntityBanService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.UpdateDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentUpdated;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateDepartmentHandler implements CommandHandler<UpdateDepartment, DepartmentUpdated> {

    @Inject
    private DepartmentsRepository repository;

    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    private EntityBanService banService;

    public Result<DepartmentUpdated> handle(UpdateDepartment command) {

        Department department = repository.findById(command.getId());
        if (department == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Department not found");
        }
        if (banService.findForEntity(IsEntityBannedV2.DEPARTMENT_BAN_ENTITY_TYPE, department.id.toString()).isPresent()) {
            return new Result<>(ErrorCode.EXCEPTION, "Department is banned");
        }

        department.name = command.getName();
        department.description = command.getDescription();


        Org parent = orgsRepository.findByName(command.getParent());

        if (parent == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Parent not found");
        }

        department.parent = parent.name;

        repository.update(department);

        DepartmentUpdated result = new DepartmentUpdated(department.id.toString(), department.name, department.description, department.parent);

        return new Result<>(result);
    }
}