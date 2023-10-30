package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateProject;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.ProjectsRepository;
import edu.kmaooad.capstone23.competences.events.ProjUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class UpdateProjectHandler implements CommandHandler<UpdateProject, ProjUpdated> {
    @Inject
    ProjectsRepository repository; //intentionally left non-private: https://stackoverflow.com/questions/55101095/why-does-quarkus-warn-me-about-injection-in-private-fields

    @Override
    public Result<ProjUpdated> handle(UpdateProject command) {
        var foundProj = repository.findProjectById(command.getId());

        if(foundProj == null)
            return new Result<>(ErrorCode.EXCEPTION, "Updated");

        var newValues = new Project();
        newValues.id = command.getId();
        newValues.name = command.getName();
        newValues.description = command.getDescription();
        newValues.skills = command.getSkills();

        repository.updateProject(newValues);
        var result = new ProjUpdated(
                newValues.id.toString(),
                newValues.name,
                newValues.description,
                newValues.skills.stream().map(ObjectId::new).toList(),
                newValues.skillSets.stream().map(ObjectId::new).toList()
        );

        return new Result<>(result);
    }
}
