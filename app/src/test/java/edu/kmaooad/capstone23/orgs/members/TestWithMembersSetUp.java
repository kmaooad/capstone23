package edu.kmaooad.capstone23.orgs.members;

import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.orgs.dal.Org;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class TestWithMembersSetUp extends TestWithDbClearance {

    protected ObjectId firstOrg;
    protected List<ObjectId> firstOrgMembers;
    protected ObjectId secondOrg;
    protected List<ObjectId> secondOrgMembers;

    @BeforeEach
    void setUp() {
        var fstOrg = new Org();
        fstOrg.name = "KMA";
        orgsRepository.insert(fstOrg);
        firstOrg = fstOrg.id;

        firstOrgMembers = new ArrayList<>();
        Member member1 = new Member();
        member1.firstName = "First";
        member1.lastName = "Member";
        member1.orgId = firstOrg;
        member1.email = "fstMember@email.com";
        membersRepository.insert(member1);
        firstOrgMembers.add(member1.orgId);

        Member member2 = new Member();
        member2.firstName = "Second";
        member2.lastName = "Member";
        member2.orgId = firstOrg;
        member2.email = "sndMember@email.com";
        membersRepository.insert(member2);
        firstOrgMembers.add(member2.orgId);

        Member member3 = new Member();
        member3.firstName = "Third";
        member3.lastName = "Member";
        member3.orgId = firstOrg;
        member3.email = "thrdMember@email.com";
        membersRepository.insert(member3);
        firstOrgMembers.add(member3.orgId);

        var sndOrg = new Org();
        sndOrg.name = "KMA";
        orgsRepository.insert(sndOrg);
        firstOrg = sndOrg.id;

        secondOrgMembers = new ArrayList<>();
        Member member4 = new Member();
        member4.firstName = "Third";
        member4.lastName = "Member";
        member4.orgId = secondOrg;
        member4.email = "frthMember@email.com";
        membersRepository.insert(member4);
        secondOrgMembers.add(member4.orgId);
    }
}
