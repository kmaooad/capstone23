package edu.kmaooad.capstone23.orgs.members;

import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;

public class TestWithDbClearance {
    @Inject
    protected MembersRepository membersRepository;

    @Inject
    protected ExpertsRepository expertsRepository;

    @Inject
    protected OrgsRepository orgsRepository;

    @Inject
    UserRepository userRepository;

    @AfterEach
    void clearMemberCollection() {
        expertsRepository.deleteAll();
        membersRepository.deleteAll();
        orgsRepository.deleteAll();
        userRepository.deleteAll();
    }
}
