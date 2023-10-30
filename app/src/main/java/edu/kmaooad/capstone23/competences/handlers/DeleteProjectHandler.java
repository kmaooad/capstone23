package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteProject;
import edu.kmaooad.capstone23.competences.dal.ProjectsRepository;
import edu.kmaooad.capstone23.competences.events.ProjDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteProjectHandler implements CommandHandler<DeleteProject, ProjDeleted> {
    @Inject
    ProjectsRepository repository; //intentionally left non-private: https://stackoverflow.com/questions/55101095/why-does-quarkus-warn-me-about-injection-in-private-fields

    @Override
    public Result<ProjDeleted> handle(DeleteProject command) {
        var projId = command.getId();
        var foundProj = repository.findProjectById(String.valueOf(projId));
        if(foundProj != null) {
            repository.deleteProject(foundProj);
            var result = new ProjDeleted(new ObjectId((projId)));
            return new Result<>(result);
        } else {
            return new Result<>(ErrorCode.EXCEPTION,
                    "Unable to delete a non-existent project.");
        }
    }
}