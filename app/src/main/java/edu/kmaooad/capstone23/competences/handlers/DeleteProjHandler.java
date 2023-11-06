package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteProj;
import edu.kmaooad.capstone23.competences.dal.ProjectMapper;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.competences.dal.dto.ProjectRequestDto;
import edu.kmaooad.capstone23.competences.dal.dto.ProjectResponseDto;
import edu.kmaooad.capstone23.competences.events.ProjDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteProjHandler implements CommandHandler<DeleteProj, ProjDeleted> {
    @Inject
    ProjsRepository repository; //intentionally left non-private: https://stackoverflow.com/questions/55101095/why-does-quarkus-warn-me-about-injection-in-private-fields
    ProjectMapper projectMapper;

    @Override
    public Result<ProjDeleted> handle(DeleteProj command) {
        projectMapper = new ProjectMapper();
        var projId = command.getId();
        ProjectResponseDto projectResponseDto = projectMapper.toDto(repository.findById(projId));
        if(projectResponseDto != null) {
            ProjectRequestDto projectRequestDto = new ProjectRequestDto();
            projectRequestDto.setId(projectResponseDto.getId().toHexString());
            projectRequestDto.setName(projectResponseDto.getName());
            projectRequestDto.setDescription(projectResponseDto.getDescription());
            projectRequestDto.setSkills(projectResponseDto.getSkills().stream()
                    .map(ObjectId::toHexString)
                    .toList());
            projectRequestDto.setSkillSets(projectResponseDto.getSkillSets().stream()
                    .map(ObjectId::toHexString)
                    .toList());
            repository.delete(projectMapper.toModel(projectRequestDto));
            var result = new ProjDeleted(projId);
            return new Result<>(result);
        } else {
            return new Result<>(ErrorCode.EXCEPTION,
                    "Unable to delete a non-existent project.");
        }
    }
}