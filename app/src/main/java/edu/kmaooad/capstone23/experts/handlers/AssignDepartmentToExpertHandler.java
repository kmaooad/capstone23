package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import edu.kmaooad.capstone23.experts.commands.AssignDepartmentToExpert;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.DepartmentAssignedToExpert;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;

@RequestScoped
public class AssignDepartmentToExpertHandler
        implements CommandHandler<AssignDepartmentToExpert, DepartmentAssignedToExpert> {
    @Inject
    ExpertsRepository expertsRepository;
    @Inject
    DepartmentService departmentService;

    @Override
    public Result<DepartmentAssignedToExpert> handle(AssignDepartmentToExpert command) {
        Expert expert = expertsRepository.findById(new ObjectId(command.getExpertId()));
        Department department = departmentService.findById(command.getDepartmentId());

        if (expert == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Expert not found");
        } else if (department == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Department not found");
        }

        if (expert.departments == null) {
            expert.departments = new ArrayList<>();
        }

        if (expert.departments.stream().anyMatch(p -> p.id.equals(department.id))) {
            return new Result<>(ErrorCode.CONFLICT, "Expert is already in this department");
        }

        expert.departments.add(department);
        expertsRepository.modify(expert);

        return new Result<>(new DepartmentAssignedToExpert(expert));
    }
}
