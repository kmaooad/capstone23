package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateDepartmentHandler implements CommandHandler<CreateDepartment, DepartmentCreated> {

    @Inject
    private DepartmentsRepository repository;
    @Inject
    private OrgsRepository orgsRepository;

    public Result<DepartmentCreated> handle(CreateDepartment command) {

        Department department = new Department();
        department.name = command.getName();
        department.description = command.getDescription();

        Org parent = orgsRepository.findByName(command.getParent());
        if (parent == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Parent not found");
        }
        department.parent = parent.name;

        repository.insert(department);

        DepartmentCreated result = new DepartmentCreated(department.id.toString());

        return new Result<>(result);
    }
}