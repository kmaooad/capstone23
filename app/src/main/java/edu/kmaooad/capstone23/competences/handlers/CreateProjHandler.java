package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateProj;
import edu.kmaooad.capstone23.competences.dal.ProjectMapper;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.competences.dal.dto.ProjectRequestDto;
import edu.kmaooad.capstone23.competences.dal.dto.ProjectResponseDto;
import edu.kmaooad.capstone23.competences.events.ProjCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class CreateProjHandler implements CommandHandler<CreateProj, ProjCreated> {
    @Inject
    ProjsRepository repository; //intentionally left non-private: https://stackoverflow.com/questions/55101095/why-does-quarkus-warn-me-about-injection-in-private-fields
    ProjectMapper projectMapper;

    @Override
    public Result<ProjCreated> handle(CreateProj command) {
        projectMapper = new ProjectMapper();

        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setName(command.getName());
        projectRequestDto.setDescription(command.getDescription());
        projectRequestDto.setSkills(command.getSkills().stream()
                .map(ObjectId::toHexString)
                .toList());
        projectRequestDto.setSkillSets(command.getSkillSets().stream()
                .map(ObjectId::toHexString)
                .toList());

        ProjectResponseDto projectResponseDto =
                projectMapper.toDto(repository.insert(projectMapper.toModel(projectRequestDto)));

        if (projectResponseDto != null) {
            var event = new ProjCreated(projectResponseDto.id.toString());
            return new Result<>(event);
        } else {
            return new Result<>(ErrorCode.EXCEPTION,
                    "Trying to create an already existent project.");
        }
    }
}
