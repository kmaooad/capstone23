package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.ExpertType;
import edu.kmaooad.capstone23.experts.commands.UpdateExpert;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertRequestDto;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertResponseDto;
import edu.kmaooad.capstone23.experts.events.ExpertUpdated;
import edu.kmaooad.capstone23.experts.service.ExpertMapper;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class UpdateExpertHandler implements CommandHandler<UpdateExpert, ExpertUpdated> {

    @Inject
    private ExpertService expertService;
    @Inject
    private OrgsRepository orgsRepository;
    ExpertMapper expertMapper;

    @Override
    public Result<ExpertUpdated> handle(UpdateExpert command) {
        var expertRequestDto = new ExpertRequestDto();
        expertRequestDto.setId(command.getId());
        expertRequestDto.setName(command.getExpertName());
        expertRequestDto.setOrgId(command.getOrgId());
        try {
            var updatedExpert = expertService.modify(expertMapper.toModel(expertRequestDto));
            return new Result<>(new ExpertUpdated(updatedExpert));
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, e.getMessage());
        }
    }
}
