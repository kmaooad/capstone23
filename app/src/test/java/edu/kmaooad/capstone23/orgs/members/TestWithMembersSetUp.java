package edu.kmaooad.capstone23.orgs.members;

import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.users.interfaces.UserRepository;
import edu.kmaooad.capstone23.users.mocks.UserMocks;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class TestWithMembersSetUp {

    protected ObjectId firstOrg;
    protected List<ObjectId> firstOrgMembers;
    protected ObjectId secondOrg;
    protected List<ObjectId> secondOrgMembers;

    @Inject
    protected MembersRepository membersRepository;

    @Inject
    protected OrgsRepository orgsRepository;

    @Inject
    protected UserRepository userRepository;

    @BeforeEach
    void setUp() {
        orgsRepository.deleteAll();
        membersRepository.deleteAll();
        var fstOrg = new Org();
        fstOrg.name = "KMA";
        orgsRepository.insert(fstOrg);
        firstOrg = fstOrg.id;

        var user1 = userRepository.insert(UserMocks.validUser());
        var user2 = userRepository.insert(UserMocks.validUser());
        var user3 = userRepository.insert(UserMocks.validUser());
        var user4 = userRepository.insert(UserMocks.validUser());

        firstOrgMembers = new ArrayList<>();
        Member member1 = new Member();
        member1.orgId = firstOrg;
        member1.userId = user1.id;
        membersRepository.insert(member1);
        firstOrgMembers.add(member1.id);

        Member member2 = new Member();
        member2.orgId = firstOrg;
        member2.userId = user2.id;
        membersRepository.insert(member2);
        firstOrgMembers.add(member2.id);

        Member member3 = new Member();
        member3.orgId = firstOrg;
        member3.userId = user3.id;
        membersRepository.insert(member3);
        firstOrgMembers.add(member3.id);

        var sndOrg = new Org();
        sndOrg.name = "KMA";
        orgsRepository.insert(sndOrg);
        secondOrg = sndOrg.id;

        secondOrgMembers = new ArrayList<>();
        Member member4 = new Member();
        member4.orgId = secondOrg;
        member4.userId = user4.id;
        membersRepository.insert(member4);
        secondOrgMembers.add(member4.id);
    }

    protected void createOrgWithMember(String email) {
        var org = new Org();
        org.name = "Ubisoft";
        orgsRepository.insert(org);

        var user1 = userRepository.insert(UserMocks.userWithGivenEmail(email));

        firstOrgMembers = new ArrayList<>();
        Member member = new Member();
        member.orgId = org.id;
        member.userId = user1.id;
        membersRepository.insert(member);
    }
}
