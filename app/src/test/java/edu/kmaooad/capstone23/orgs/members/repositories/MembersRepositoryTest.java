package edu.kmaooad.capstone23.orgs.members.repositories;

import com.mongodb.MongoException;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.exceptions.MemberNotFoundException;
import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.members.TestWithDbClearance;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class MembersRepositoryTest extends TestWithDbClearance {
    @Inject
    MembersRepository membersRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    OrgsRepository orgsRepository;

    protected Member setUpMember;

    @BeforeEach
    void setUp() {
        var member = new Member();
        var org = new Org();
        org.name = "NaUKMA";
        orgsRepository.insert(org);
        member.orgId = org.id;
        var user = userRepository.insert(UserMocks.validUser());
        member.userId = user.id;
        membersRepository.persist(member);
        setUpMember = member;
    }

    @Test
    public void testInsertDuplicateOrgAndUserId() {
        Member newMember = new Member();
        newMember.orgId = setUpMember.orgId;
        newMember.userId = setUpMember.userId;

        assertThrows(UniquenessViolationException.class, () -> membersRepository.insert(newMember));
    }

    @Test
    public void testInsertDuplicateOrgAndUserIdsGeneratedMethodThrowsMongoException() {
        Member newMember = new Member();
        newMember.orgId = setUpMember.orgId;
        newMember.userId = setUpMember.userId;

        assertThrows(MongoException.class, () -> membersRepository.persist(newMember));
    }

    @Test
    public void testUpdateToHaveWithDuplicatedUserAndOrgId() {
        Member newMember = new Member();
        newMember.orgId = setUpMember.orgId;
        newMember.userId = new ObjectId();

        assertDoesNotThrow(() -> {
            membersRepository.insert(newMember);
        });

        newMember.userId = setUpMember.userId;

        assertThrows(UniquenessViolationException.class, () -> membersRepository.updateEntry(newMember));
    }

    @Test
    public void testUpdateNonExistentMember() {
        var user = userRepository.insert(UserMocks.validUser());

        Member member = new Member();
        member.id = new ObjectId();
        member.orgId = setUpMember.orgId;
        member.userId = user.id;

        assertThrows(MemberNotFoundException.class, () -> membersRepository.updateEntry(member));
    }
}
