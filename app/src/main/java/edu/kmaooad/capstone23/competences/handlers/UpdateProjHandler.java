package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.UpdateProj;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.ProjectMapper;
import edu.kmaooad.capstone23.competences.dal.dto.ProjectRequestDto;
import edu.kmaooad.capstone23.competences.dal.dto.ProjectResponseDto;
import edu.kmaooad.capstone23.competences.events.ProjUpdated;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class UpdateProjHandler implements CommandHandler<UpdateProj, ProjUpdated> {
    @Inject
    ProjsRepository repository; //intentionally left non-private: https://stackoverflow.com/questions/55101095/why-does-quarkus-warn-me-about-injection-in-private-fields
    ProjectMapper projectMapper;

    @Override
    public Result<ProjUpdated> handle(UpdateProj command) {
        projectMapper = new ProjectMapper();

        ProjectResponseDto projectResponseDto = projectMapper.toDto(repository.findById(command.getId()));
        if(projectResponseDto == null)
            return new Result<>(ErrorCode.EXCEPTION, "Updated");

        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setId(command.getId().toHexString());
        projectRequestDto.setName(command.getName());
        projectRequestDto.setDescription(command.getDescription());
        projectRequestDto.setSkills(command.getSkills().stream()
                .map(ObjectId::toHexString)
                .toList());
        projectRequestDto.setSkillSets(command.getSkillSets().stream()
                .map(ObjectId::toHexString)
                .toList());

        repository.update(projectMapper.toModel(projectRequestDto));
        var result = new ProjUpdated(new ObjectId(projectRequestDto.getId()), projectRequestDto.getName(),
                projectRequestDto.getDescription(),
                projectRequestDto.getSkills().stream().map(ObjectId::new).toList(),
                projectRequestDto.getSkillSets().stream().map(ObjectId::new).toList());
        return new Result<>(result);
    }
}
