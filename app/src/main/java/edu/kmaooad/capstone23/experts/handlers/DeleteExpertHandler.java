package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.DeleteExpert;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertRequestDto;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertResponseDto;
import edu.kmaooad.capstone23.experts.events.ExpertDeleted;
import edu.kmaooad.capstone23.experts.service.ExpertMapper;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteExpertHandler implements CommandHandler<DeleteExpert, ExpertDeleted> {

    @Inject
    private ExpertService expertService;
    ExpertMapper expertMapper;

    public Result<ExpertDeleted> handle(DeleteExpert command) {
        expertMapper = new ExpertMapper();

        ObjectId id = command.getId();
        ExpertResponseDto expertResponseDto = expertMapper.toDto(expertService.findById(id));

        if (expertResponseDto == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Expert not found");
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

        expertService.deleteExpert(expertMapper.toModel(expertRequestDto));

        return new Result<ExpertDeleted>(new ExpertDeleted(expertResponseDto.getId().toString()));
    }
}
