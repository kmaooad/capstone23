package edu.kmaooad.capstone23.orgs.members.repositories;

import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.members.ClearDbMemberTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class MembersRepositoryTest extends ClearDbMemberTest {
    @Inject
    MembersRepository membersRepository;

    @Inject
    OrgsRepository orgsRepository;

    @BeforeEach
    void setUp() {
        var member = new Member();
        var org = new Org();
        org.name = "NaUKMA";
        orgsRepository.insert(org);
        member.orgId = org.id;
        member.lastName = "last";
        member.firstName = "first";
        member.email = "email@email.com";
        membersRepository.persist(member);
    }

    @Test
    public void testInsertDuplicateEmail() {
        var email = "email@email.com";

        Member newMember = new Member();
        newMember.email = email;

        assertThrows(UniquenessViolationException.class, () -> membersRepository.insert(newMember));
    }
}
