package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertRequestDto;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertResponseDto;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.experts.service.ExpertMapper;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateExpertHandler implements CommandHandler<CreateExpert, ExpertCreated> {

    @Inject
    private ExpertService expertService;
    @Inject
    private OrgsRepository orgsRepository;
    ExpertMapper expertMapper;

    public Result<ExpertCreated> handle(CreateExpert command) {
        expertMapper = new ExpertMapper();

        ExpertRequestDto expertRequestDto = new ExpertRequestDto();
        expertRequestDto.setName(command.getExpertName());
        expertRequestDto.setOrgId(orgsRepository.findByName(command.getOrgName()).id.toHexString());

        if (expertRequestDto.getOrgId() == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Organisation not found");
        }

        ExpertResponseDto expertResponseDto =
                expertMapper.toDto(expertService.insert(expertMapper.toModel(expertRequestDto)));

        ExpertCreated result = new ExpertCreated(expertResponseDto.getId().toString(),
                expertResponseDto.getOrg());

        return new Result<ExpertCreated>(result);
    }
}
