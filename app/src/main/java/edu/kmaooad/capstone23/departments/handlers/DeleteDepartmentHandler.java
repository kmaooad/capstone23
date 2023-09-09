package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.DeleteDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteDepartmentHandler implements CommandHandler<DeleteDepartment, DepartmentDeleted> {

    @Inject
    private DepartmentsRepository repository;

    public Result<DepartmentDeleted> handle(DeleteDepartment command) {
        Department department = repository.findById(command.getId());
        if (department == null) {
            return new Result(ErrorCode.EXCEPTION, "Department not found");
        }

        repository.delete(department);

        DepartmentDeleted result = new DepartmentDeleted(department.id.toString());
        return new Result(result);
    }
}

