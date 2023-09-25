package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.experts.commands.AssignDepartmentToExpert;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.DepartmentAssignedToExpert;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import org.bson.types.ObjectId;

@RequestScoped
public class AssignDepartmentToExpertHandler
        implements CommandHandler<AssignDepartmentToExpert, DepartmentAssignedToExpert> {
    @Inject
    ExpertsRepository expertsRepository;
    @Inject
    DepartmentsRepository departmentsRepository;

    @Override
    public Result<DepartmentAssignedToExpert> handle(AssignDepartmentToExpert command) {
        Expert expert = expertsRepository.findById(new ObjectId(command.getExpertId()));
        Department department = departmentsRepository.findById(command.getDepartmentId());

        if (expert == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Expert not found");
        } else if (department == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Department not found");
        }

        if (expert.departments == null) {
            expert.departments = new ArrayList<>();
        }
        expert.departments.add(department);
        expertsRepository.modify(expert);

        return new Result<>(new DepartmentAssignedToExpert(expert));
    }
}
