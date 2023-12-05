package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.DeleteDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentDeleted;
import edu.kmaooad.capstone23.events.EventManager;
import edu.kmaooad.capstone23.events.EventType;
import edu.kmaooad.capstone23.events.SystemEvent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteDepartmentHandler implements CommandHandler<DeleteDepartment, DepartmentDeleted> {
    @Inject
    EventManager eventManager;
    @Inject
    private DepartmentsRepository repository;

    public Result<DepartmentDeleted> handle(DeleteDepartment command) {
        Department department = repository.findById(command.getId());
        if (department == null) {
            return new Result(ErrorCode.EXCEPTION, "Department not found");
        }

        repository.delete(department);
        eventManager.notify(new SystemEvent(EventType.DEPARTMENT_DELETED, "Department " + department.name + " was deleted!"));

        DepartmentDeleted result = new DepartmentDeleted(department.id.toString());
        return new Result(result);
    }
}

