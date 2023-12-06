package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import edu.kmaooad.capstone23.events.EventManager;
import edu.kmaooad.capstone23.events.EventType;
import edu.kmaooad.capstone23.events.SystemEvent;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateDepartmentHandler implements CommandHandler<CreateDepartment, DepartmentCreated> {

    @Inject
    private DepartmentService service;
    @Inject
    private OrgsRepository orgsRepository;

    @Inject
    EventManager eventManager;

    public Result<DepartmentCreated> handle(CreateDepartment command) {
        Org parent = orgsRepository.findByName(command.getParent());
        if (parent == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Parent not found");
        }

        Department department = service.createDepartment(command.getName(), command.getDescription(), parent.name);

        eventManager.notify(new SystemEvent(EventType.DEPARTMENT_CREATED, "Department " + department.name + " was created!"));

        DepartmentCreated result = new DepartmentCreated(department.id.toString());

        return new Result<>(result);
    }
}