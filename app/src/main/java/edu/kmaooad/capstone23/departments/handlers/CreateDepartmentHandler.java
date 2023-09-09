package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.dal.Departments;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentsCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateDepartmentHandler implements CommandHandler<CreateDepartment, DepartmentsCreated> {

    @Inject
    private DepartmentsRepository repository;

    public Result<DepartmentsCreated> handle(CreateDepartment command) {

        Departments departments = new Departments();
        departments.name = command.getName();

        repository.insert(departments);

        DepartmentsCreated result = new DepartmentsCreated(departments.id.toString());

        return new Result<DepartmentsCreated>(result);
    }
}