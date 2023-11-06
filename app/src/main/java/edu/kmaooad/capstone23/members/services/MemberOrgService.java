package edu.kmaooad.capstone23.members.services;

import edu.kmaooad.capstone23.members.dto.OrgDTO;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface MemberOrgService {
    Optional<OrgDTO> findByEmailDomainOptional(String emailDomain);

    Optional<OrgDTO> findByIdOptional(ObjectId id);
}
