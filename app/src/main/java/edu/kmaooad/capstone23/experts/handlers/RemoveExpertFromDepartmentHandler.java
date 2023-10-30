package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromDepartment;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertRequestDto;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertResponseDto;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromDepartment;
import edu.kmaooad.capstone23.experts.service.ExpertMapper;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class RemoveExpertFromDepartmentHandler
        implements CommandHandler<RemoveExpertFromDepartment, ExpertRemovedFromDepartment> {
    @Inject
    ExpertService expertService;
    ExpertMapper expertMapper;

    @Override
    public Result<ExpertRemovedFromDepartment> handle(RemoveExpertFromDepartment command) {
        expertMapper = new ExpertMapper();
        ObjectId expertId = command.getExpertId();
        ObjectId departmentId = command.getDepartmentId();
        if (expertId != null && departmentId != null) {
            ExpertResponseDto expertResponseDto = expertMapper.toDto(expertService.findById(expertId));

            if (expertResponseDto.getDepartments().isEmpty()) {
                return new Result<>(ErrorCode.NOT_FOUND, "Expert has no department");
            }

            if (!expertResponseDto.getDepartments().stream().anyMatch(p -> p.id.equals(departmentId))) {
                return new Result<>(ErrorCode.CONFLICT, "Expert is not in this department");
            }

            ExpertRequestDto expertRequestDto = new ExpertRequestDto();
            expertRequestDto.setName(expertResponseDto.getName());
            expertRequestDto.setOrgId(expertResponseDto.getOrg().id.toHexString());
            expertRequestDto.setDepartmentIds(expertResponseDto.getDepartments().stream()
                    .filter(p -> p.id == departmentId)
                    .map(d -> d.id.toHexString())
                    .toList());
            expertRequestDto.setProjectsIds(expertResponseDto.getProjects().stream()
                    .map(p -> p.id.toHexString())
                    .toList());


            expertService.modify(expertMapper.toModel(expertRequestDto));

            return new Result<>(new ExpertRemovedFromDepartment(expertResponseDto.getId().toString()));
        }
        return new Result<>(ErrorCode.NOT_FOUND, "Either expert or department null");
    }
}
