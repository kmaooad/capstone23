package edu.kmaooad.capstone23.members.services;

import edu.kmaooad.capstone23.members.dto.OrgDTO;

import java.util.Optional;

public interface OrgService {
    Optional<OrgDTO> findByEmailDomainOptional(String emailDomain);
}
