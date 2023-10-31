package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateProject;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.events.ProjUpdated;
import edu.kmaooad.capstone23.competences.services.ProjectService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class UpdateProjectHandler implements CommandHandler<UpdateProject, ProjUpdated> {
    @Inject
    ProjectService service; //intentionally left non-private: https://stackoverflow.com/questions/55101095/why-does-quarkus-warn-me-about-injection-in-private-fields

    @Override
    public Result<ProjUpdated> handle(UpdateProject command) {
        var foundProj = service.findById(command.getId());

        if (foundProj == null)
            return new Result<>(ErrorCode.EXCEPTION, "Updated");

        var newValues = new Project();
        newValues.id = command.getId();
        newValues.name = command.getName();
        newValues.description = command.getDescription();
        newValues.skills = command.getSkills();

        service.update(newValues);
        var result = new ProjUpdated(
                newValues.id,
                newValues.name,
                newValues.description,
                newValues.skills.stream().map(ObjectId::new).toList(),
                newValues.skillSets.stream().map(ObjectId::new).toList()
        );

        return new Result<>(result);
    }
}
