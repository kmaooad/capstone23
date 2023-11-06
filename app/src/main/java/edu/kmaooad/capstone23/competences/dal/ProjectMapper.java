package edu.kmaooad.capstone23.competences.dal;

import edu.kmaooad.capstone23.competences.dal.dto.ProjectRequestDto;
import edu.kmaooad.capstone23.competences.dal.dto.ProjectResponseDto;
import org.bson.types.ObjectId;

public class ProjectMapper {

    public ProjectResponseDto toDto(Project project) {
        if (project != null) {
            ProjectResponseDto dto = new ProjectResponseDto();
            dto.setId(project.id);
            dto.setName(project.name);
            dto.setDescription(project.description);
            dto.setSkills(project.skills);
            dto.setSkillSets(project.skillSets);
            return dto;
        }
        return null;
    }

    public Project toModel(ProjectRequestDto dto) {
        if (dto != null) {
            Project project = new Project();
            project.id = new ObjectId(dto.getId());
            project.name = dto.getName();
            project.description = dto.getDescription();
            project.skills = dto.getSkills().stream()
                    .map(ObjectId::new)
                    .toList();
            project.skillSets = dto.getSkillSets().stream()
                    .map(ObjectId::new)
                    .toList();
            return project;
        }
        return null;
    }
}
