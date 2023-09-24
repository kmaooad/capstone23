package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromDepartment;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromMember;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromDepartment;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromMember;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
@RequestScoped
public class RemoveExpertFromDepartmentHandler
        implements CommandHandler<RemoveExpertFromDepartment, ExpertRemovedFromDepartment> {
    @Inject
    ExpertsRepository expertsRepository;
    @Inject
    DepartmentsRepository departmentsRepository;

    @Override
    public Result<ExpertRemovedFromDepartment> handle(RemoveExpertFromDepartment command) {
        ObjectId expertId = command.getExpertId();
        ObjectId departmentId = command.getDepartmentId();
        if (expertId != null && departmentId != null) {
            Expert expert = expertsRepository.findById(expertId);
            Department department = departmentsRepository.findById(departmentId);

            if (expert.department.isEmpty()) {
                return new Result<>(ErrorCode.NOT_FOUND, "Expert has no department");
            }

            if (!expert.department.contains(department)) {
                return new Result<>(ErrorCode.CONFLICT, "Expert is not in this department");
            }

            expert.department.remove(department);
            expertsRepository.modify(expert);

            return new Result<>(new ExpertRemovedFromDepartment(expert.id.toString()));
        }
        return new Result<>(ErrorCode.NOT_FOUND, "Either expert or department null");
    }
}
