package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.orgs.services.OrgsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateExpertHandler implements CommandHandler<CreateExpert, ExpertCreated> {

    @Inject
    private ExpertsRepository expertsRepository;
    @Inject
    OrgsService orgsService;

    public Result<ExpertCreated> handle(CreateExpert command) {

        Expert expert = new Expert();
        expert.name = command.getExpertName();
        expert.org = orgsService.findByName(command.getOrgName());

        if (expert.org == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Organisation not found");
        }

        expertsRepository.insert(expert);

        ExpertCreated result = new ExpertCreated(expert.id.toString(), expert.org);

        return new Result<>(result);
    }
}
