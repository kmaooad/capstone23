package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.experts.commands.AssignExpertToProject;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.events.ExpertAssignedToProject;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@RequestScoped
public class AssignExpertToProjectHandler implements CommandHandler<AssignExpertToProject, ExpertAssignedToProject> {

    @Inject
    private ProjsRepository projsRepository;
    @Inject
    private ExpertsRepository expertsRepository;

    public Result<ExpertAssignedToProject> handle(AssignExpertToProject command) {
        ObjectId projId = command.getProjectId();
        ObjectId expertId = command.getExpertId();
        Project proj = projsRepository.findById(projId);
        Expert expert = expertsRepository.findById(expertId);

        if (proj == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Project not found");
        }

        if (expert == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Expert not found");
        }

        if (expert.projects != null) {
            if (expert.projects.stream().anyMatch(p -> p.id.equals(projId))) {
                return new Result<>(ErrorCode.CONFLICT, "Expert is already assigned to project");
            }
            expert.projects.add(proj);
        } else {
            expert.projects = List.of(proj);
        }

        expertsRepository.modify(expert);

        return new Result<ExpertAssignedToProject>(new ExpertAssignedToProject(expert.id.toString()));
    }
}