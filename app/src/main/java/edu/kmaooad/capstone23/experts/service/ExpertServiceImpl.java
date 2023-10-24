package edu.kmaooad.capstone23.experts.service;

import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriBuilder;
import org.bson.types.ObjectId;

@ApplicationScoped
public class ExpertServiceImpl implements ExpertService {

    @Inject
    private ExpertsRepository expertsRepository;

    @Override
    public Expert findById(ObjectId id) {
        return expertsRepository.findById(id);
    }

    public String createInvitationLink(ObjectId invitationId) {
        String host = System.getProperty("quarkus.http.host");
        var invitationLink = UriBuilder.fromUri(host);
        invitationLink.path(ACCEPT_INVITATION_ENDPOINT);
        invitationLink.queryParam("id", invitationId.toString());
        return invitationLink.build().toString();
    }

    public Expert insert(Expert expert) {
        return expertsRepository.insert(expert);
    }

    public void deleteExpert(Expert expert) {
        expertsRepository.delete(expert);
    }

    public Expert modify(Expert expert) throws IllegalArgumentException {
       return expertsRepository.modify(expert);
    }

    public Expert findByMemberId(ObjectId memberId) {
        return expertsRepository.find("memberId", memberId).firstResult();
    }
}
