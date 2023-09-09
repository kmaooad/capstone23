package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateExpert;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateExpertHandler implements CommandHandler<CreateExpert, ExpertCreated> {

    @Inject
    ExpertsRepository repository;

    public Result<ExpertCreated> handle(CreateExpert command) {

        Expert expert = new Expert();
        expert.name = command.getExpertName();

        repository.insert(expert);

        ExpertCreated result = new ExpertCreated(expert.id.toString());

        return new Result<ExpertCreated>(result);
    }
}
