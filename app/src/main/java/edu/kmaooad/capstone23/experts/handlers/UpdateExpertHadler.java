package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.DeleteExpert;
import edu.kmaooad.capstone23.experts.commands.UpdateExpert;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertDeleted;
import edu.kmaooad.capstone23.experts.events.ExpertUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateExpertHadler implements CommandHandler<UpdateExpert, ExpertUpdated> {

    @Inject
    ExpertsRepository repository;

    @Override
    public Result<ExpertUpdated> handle(UpdateExpert command) {
        Expert expert = new Expert();
        expert.id = command.getExpertId();

        repository.delete(expert);

        ExpertUpdated result = new ExpertUpdated(expert.id.toString());

        return new Result<ExpertUpdated>(result);
    }
}
