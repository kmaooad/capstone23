package edu.kmaooad.capstone23.experts.service;

import edu.kmaooad.capstone23.competences.dal.ProjsRepository;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertRequestDto;
import edu.kmaooad.capstone23.experts.dal.dto.ExpertResponseDto;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

public class ExpertMapper {
    @Inject
    OrgsRepository orgsRepository;
    @Inject
    DepartmentsRepository departmentsRepository;
    @Inject
    ProjsRepository projsRepository;

    public ExpertResponseDto toDto(Expert expert) {
        if (expert != null) {
            ExpertResponseDto expertResponseDto = new ExpertResponseDto();
            expertResponseDto.setId(expert.id);
            expertResponseDto.setMemberId(expert.memberId);
            expertResponseDto.setName(expert.name);
            expertResponseDto.setOrg(expert.org);
            expertResponseDto.setDepartments(expert.departments);
            expertResponseDto.setProjects(expert.projects);
            return expertResponseDto;
        }
        return null;
    }

    public Expert toModel(ExpertRequestDto expertRequestDto) {
        if (expertRequestDto != null) {
            Expert expert = new Expert();
            expert.id = new ObjectId(expertRequestDto.getId());
            expert.memberId = new ObjectId(expertRequestDto.getMemberId());
            expert.name = expertRequestDto.getName();
            expert.org = orgsRepository.findById(expertRequestDto.getOrgId());
            expert.departments = departmentsRepository.findAll().stream()
                    .filter(d -> expertRequestDto.getDepartmentIds().contains(d.id.toHexString()))
                    .toList();
            expert.projects = projsRepository.findAll().stream()
                    .filter(p -> expertRequestDto.getProjectsIds().contains(p.id.toHexString()))
                    .toList();
            return expert;
        }
        return null;
    }
}
