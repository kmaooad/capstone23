package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.UpdateExpert;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateExpertHandler implements CommandHandler<UpdateExpert, ExpertUpdated> {

    @Inject
    ExpertsRepository repository;

    @Override
    public Result<ExpertUpdated> handle(UpdateExpert command) {
        var expert = new Expert();
        expert.id = command.getId();
        expert.name = command.getExpertName();
        expert.org = command.getOrg();
        try {
            var updatedExpert = repository.modify(expert);
            return new Result<>(new ExpertUpdated(updatedExpert));
        } catch (IllegalArgumentException e) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, e.getMessage());
        }
    }
}
