package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteProj;
import edu.kmaooad.capstone23.competences.dal.ProjectsRepository;
import edu.kmaooad.capstone23.competences.events.ProjDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DeleteProjectHandler implements CommandHandler<DeleteProj, ProjDeleted> {
    @Inject
    ProjectsRepository repository; //intentionally left non-private: https://stackoverflow.com/questions/55101095/why-does-quarkus-warn-me-about-injection-in-private-fields

    @Override
    public Result<ProjDeleted> handle(DeleteProj command) {
        var projId = command.getId();
        var foundProj = repository.findProjectById(String.valueOf(projId));
        if(foundProj != null) {
            repository.deleteProject(foundProj);
            var result = new ProjDeleted(projId);
            return new Result<>(result);
        } else {
            return new Result<>(ErrorCode.EXCEPTION,
                    "Unable to delete a non-existent project.");
        }
    }
}