package edu.kmaooad.capstone23.members.services;

import edu.kmaooad.capstone23.orgs.dal.Org;

import java.util.Optional;

public interface OrgService {
    Optional<Org> findByEmailDomainOptional(String emailDomain);
}
