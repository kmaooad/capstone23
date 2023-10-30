package edu.kmaooad.capstone23.members.services;

import edu.kmaooad.capstone23.members.dto.OrgDTO;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface OrgService {
    Optional<OrgDTO> findByEmailDomainOptional(String emailDomain);

    Optional<OrgDTO> findByIdOptional(String id);
}
