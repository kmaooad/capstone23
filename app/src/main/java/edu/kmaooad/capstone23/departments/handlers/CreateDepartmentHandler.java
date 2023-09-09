package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.dal.Departments;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.events.DepartmentsCreated;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateDepartmentHandler implements CommandHandler<CreateDepartment, DepartmentsCreated> {

    @Inject
    private DepartmentsRepository repository;
    @Inject
    private OrgsRepository orgsRepository;

    public Result<DepartmentsCreated> handle(CreateDepartment command) {

        Departments department = new Departments();
        department.name = command.getName();
        department.description = command.getDescription();

        if (command.getParent() != null) {
            Org parent = orgsRepository.findByName(command.getParent());
            department.parent = parent.id.toString();
        }

        repository.insert(department);

        DepartmentsCreated result = new DepartmentsCreated(department.id.toString());

        return new Result<DepartmentsCreated>(result);
    }
}