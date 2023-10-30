package edu.kmaooad.capstone23.experts.service;

import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitation;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitationRepository;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriBuilder;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class ExpertServiceImpl implements ExpertService {

    @Inject
    ExpertInvitationRepository expertInvitationRepository;

    @Inject
    ExpertsRepository expertsRepository;

    public String createInvitationLink(ObjectId invitationId) {
        String host = System.getProperty("quarkus.http.host");
        var invitationLink = UriBuilder.fromUri(host);
        invitationLink.path(ACCEPT_INVITATION_ENDPOINT);
        invitationLink.queryParam("id", invitationId.toString());
        return invitationLink.build().toString();
    }

    public Optional<ExpertInvitation> findInvitationById(ObjectId id) {
        return Optional.ofNullable(expertInvitationRepository.findById(id));
    }

    public void persist(ExpertInvitation expertInvitation) {
        expertInvitationRepository.persist(expertInvitation);
    }

    public void insertExpert(Expert expert) {
        expertsRepository.insert(expert);
    }

    @Override
    public Expert modifyExpert(Expert expert) {
        return expertsRepository.modify(expert);
    }
}
