package edu.kmaooad.capstone23.orgs.members;

import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;

@QuarkusTest
public class TestWithDbClearance {
    @Inject
    protected MembersRepository membersRepository;

    @Inject
    protected OrgsRepository orgsRepository;

    @BeforeEach
    void clearMemberCollection() {
        membersRepository.deleteAll();
        orgsRepository.deleteAll();
    }
}
