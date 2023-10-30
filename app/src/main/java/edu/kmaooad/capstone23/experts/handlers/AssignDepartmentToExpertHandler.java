package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.experts.commands.AssignDepartmentToExpert;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertRequestDto;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertResponseDto;
import edu.kmaooad.capstone23.experts.events.DepartmentAssignedToExpert;
import edu.kmaooad.capstone23.experts.service.ExpertMapper;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import org.bson.types.ObjectId;

@RequestScoped
public class AssignDepartmentToExpertHandler
        implements CommandHandler<AssignDepartmentToExpert, DepartmentAssignedToExpert> {

    @Inject
    ExpertService expertService;
    @Inject
    DepartmentsRepository departmentsRepository;
    ExpertMapper expertMapper;

    @Override
    public Result<DepartmentAssignedToExpert> handle(AssignDepartmentToExpert command) {
        expertMapper = new ExpertMapper();

        ExpertResponseDto expertResponseDto =
                expertMapper.toDto(expertService.findById(new ObjectId(command.getExpertId())));
        Department department = departmentsRepository.findById(command.getDepartmentId());

        if (expertResponseDto == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Expert not found");
        } else if (department == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Department not found");
        }

        if (expertResponseDto.getDepartments() == null) {
            expertResponseDto.setDepartments(new ArrayList<>());
        }

        if (expertResponseDto.getDepartments().stream().anyMatch(p -> p.id.equals(department.id))) {
            return new Result<>(ErrorCode.CONFLICT, "Expert is already in this department");
        }

        ExpertRequestDto expertRequestDto = new ExpertRequestDto();
        expertRequestDto.setId(expertRequestDto.getId());
        expertRequestDto.setMemberId(expertRequestDto.getMemberId());
        expertRequestDto.setName(expertResponseDto.getName());
        expertRequestDto.setOrgId(expertResponseDto.getOrg().id.toHexString());
        expertRequestDto.setDepartmentIds(expertResponseDto.getDepartments().stream()
                .map(d -> d.id.toHexString())
                .toList());
        expertRequestDto.setProjectsIds(expertResponseDto.getProjects().stream()
                .map(p -> p.id.toHexString())
                .toList());

        expertRequestDto.getDepartmentIds().add(department.id.toHexString());
        expertService.modify(expertMapper.toModel(expertRequestDto));

        return new Result<>(new DepartmentAssignedToExpert(expertMapper.toModel(expertRequestDto)));
    }
}
