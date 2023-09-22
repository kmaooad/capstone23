package edu.kmaooad.capstone23.experts.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpertInvitationRepository implements PanacheMongoRepository<ExpertInvitation> {
}
