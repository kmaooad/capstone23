package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.experts.commands.AssignExpertToProject;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertRequestDto;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertResponseDto;
import edu.kmaooad.capstone23.experts.events.ExpertAssignedToProject;
import edu.kmaooad.capstone23.experts.service.ExpertMapper;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.bson.types.ObjectId;

@RequestScoped
public class AssignExpertToProjectHandler implements CommandHandler<AssignExpertToProject, ExpertAssignedToProject> {

    @Inject
    private ProjsRepository projsRepository;
    @Inject
    ExpertService expertService;
    ExpertMapper expertMapper;

    public Result<ExpertAssignedToProject> handle(AssignExpertToProject command) {
        expertMapper = new ExpertMapper();

        ObjectId projId = command.getProjectId();
        ObjectId expertId = command.getExpertId();
        Project proj = projsRepository.findById(projId);
        ExpertResponseDto expertResponseDto =
                expertMapper.toDto(expertService.findById(expertId));

        if (proj == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Project not found");
        }

        if (expertResponseDto == null) {
            return new Result<>(ErrorCode.NOT_FOUND, "Expert not found");
        }

        ExpertRequestDto expertRequestDto = new ExpertRequestDto();
        expertRequestDto.setName(expertResponseDto.getName());
        expertRequestDto.setOrgId(expertResponseDto.getOrg().id.toHexString());
        expertRequestDto.setDepartmentIds(expertResponseDto.getDepartments().stream()
                .map(d -> d.id.toHexString())
                .toList());
        expertRequestDto.setProjectsIds(expertResponseDto.getProjects().stream()
                .map(p -> p.id.toHexString())
                .toList());

        if (expertResponseDto.getProjects() != null) {
            if (expertResponseDto.getProjects().stream().anyMatch(p -> p.id.equals(projId))) {
                return new Result<>(ErrorCode.CONFLICT, "Expert is already assigned to project");
            }
            expertRequestDto.getProjectsIds().add(proj.id.toHexString());
        } else {
            expertRequestDto.setProjectsIds(List.of(proj.id.toHexString()));
        }

        expertService.modify(expertMapper.toModel(expertRequestDto));

        return new Result<ExpertAssignedToProject>(new ExpertAssignedToProject(expertResponseDto.getId().toString()));
    }
}