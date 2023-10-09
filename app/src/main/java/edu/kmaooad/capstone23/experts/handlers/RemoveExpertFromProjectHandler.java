package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.RemoveExpertFromProject;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertRemovedFromProject;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class RemoveExpertFromProjectHandler
        implements CommandHandler<RemoveExpertFromProject, ExpertRemovedFromProject> {
    @Inject
    ExpertsRepository expertsRepository;

    @Override
    public Result<ExpertRemovedFromProject> handle(RemoveExpertFromProject command) {
        ObjectId expertId = command.getExpertId();
        ObjectId projectId = command.getProjectId();
        if (expertId != null && projectId != null) {
            Expert expert = expertsRepository.findById(expertId);

            if (expert.projects.isEmpty()) {
                return new Result<>(ErrorCode.NOT_FOUND, "Expert has no projects");
            }
            if (expert.projects.stream().noneMatch(p -> p.id.equals(projectId))) {
                return new Result<>(ErrorCode.CONFLICT, "Expert is not in this project");
            }

            expert.projects = expert.projects.stream().filter(x -> x.id == projectId).toList();
            expertsRepository.modify(expert);

            return new Result<>(new ExpertRemovedFromProject(expert.id.toString()));
        }
        return new Result<>(ErrorCode.NOT_FOUND, "Either expert or project null");
    }
}
