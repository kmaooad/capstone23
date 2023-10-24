package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteProj;
<<<<<<< HEAD:app/src/main/java/edu/kmaooad/capstone23/competences/handlers/DeleteProjectHandler.java
import edu.kmaooad.capstone23.competences.dal.MongoProjectRepository;
import edu.kmaooad.capstone23.competences.dal.ProjectsRepository;
||||||| 3b6644e:app/src/main/java/edu/kmaooad/capstone23/competences/handlers/DeleteProjHandler.java
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
=======
import edu.kmaooad.capstone23.competences.dal.MongoProjectRepository;
>>>>>>> main:app/src/main/java/edu/kmaooad/capstone23/competences/handlers/DeleteProjHandler.java
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