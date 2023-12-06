package edu.kmaooad.capstone23.experts.service;

import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitation;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface ExpertService {
    String ACCEPT_INVITATION_ENDPOINT = "/experts/invitation/accept";

    String createInvitationLink(ObjectId invitationId);
    Optional<ExpertInvitation> findInvitationById(ObjectId id);
    void persist(ExpertInvitation expertInvitation);
    void insertExpert(Expert expert);
}
