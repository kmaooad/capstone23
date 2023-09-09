package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.commands.DeleteExpert;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import edu.kmaooad.capstone23.experts.events.ExpertDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteExpertHandler implements CommandHandler<DeleteExpert, ExpertDeleted> {

    @Inject
    ExpertsRepository repository;

    public Result<ExpertDeleted> handle(DeleteExpert command) {

        Expert expert = new Expert();
        expert.id = command.getExpertId();

        repository.delete(expert);

        ExpertDeleted result = new ExpertDeleted(expert.id.toString());

        return new Result<ExpertDeleted>(result);
    }
}
