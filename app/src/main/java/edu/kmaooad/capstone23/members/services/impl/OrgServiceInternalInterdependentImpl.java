package edu.kmaooad.capstone23.members.services.impl;

import edu.kmaooad.capstone23.members.dto.OrgDTO;
import edu.kmaooad.capstone23.members.services.OrgServiceInterface;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class OrgServiceInternalInterdependentImpl implements OrgServiceInterface {
    @Inject
    OrgsRepository orgsRepository;

    @Override
    public Optional<OrgDTO> findByEmailDomainOptional(String emailDomain) {
        return orgsRepository.findByEmailDomainOptional(emailDomain).map(value -> new OrgDTO(value.id.toString(), value.emailDomain));
    }

    @Override
    public Optional<OrgDTO> findByIdOptional(ObjectId id) {
        return orgsRepository.findByIdOptional(id).map(value -> new OrgDTO(value.id.toString(), value.emailDomain));
    }
}
