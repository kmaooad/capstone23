package edu.kmaooad.capstone23.orgs.members;

import edu.kmaooad.capstone23.members.dal.MembersRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;

@QuarkusTest
public class ClearDbMemberTest {
    @Inject
    MembersRepository membersRepository;

    @BeforeEach
    void clearMemberCollection() {
        membersRepository.deleteAll();
    }
}
