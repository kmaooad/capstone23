package edu.kmaooad.capstone23.members.services;

import edu.kmaooad.capstone23.members.dto.OrgDTO;
import java.util.Optional;

public interface OrgServiceInterface {
    Optional<OrgDTO> findByEmailDomainOptional(String emailDomain);

    Optional<OrgDTO> findByIdOptional(String id);
}
