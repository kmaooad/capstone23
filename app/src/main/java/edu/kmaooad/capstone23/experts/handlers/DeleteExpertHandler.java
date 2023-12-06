package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.DeleteExpert;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertDeleted;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteExpertHandler implements CommandHandler<DeleteExpert, ExpertDeleted> {

    @Inject
    private ExpertService expertService;

    public Result<ExpertDeleted> handle(DeleteExpert command) {
        ObjectId id = command.getId();
        Expert expert = expertService.findExpertById(id);

        if (expert == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Expert not found");
        }

        expertService.deleteExpert(expert);

        return new Result<>(new ExpertDeleted(expert.id.toString()));
    }
}
