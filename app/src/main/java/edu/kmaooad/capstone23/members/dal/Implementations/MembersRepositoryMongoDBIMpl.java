package edu.kmaooad.capstone23.members.dal.Implementations;

import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.members.exceptions.MemberNotFoundException;
import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class MembersRepositoryMongoDBIMpl implements MembersRepository {
    @Inject
    edu.kmaooad.capstone23.members.dal.MembersRepository membersRepository;

    @Override
    public Member insert(Member member) throws UniquenessViolationException {
        return membersRepository.insert(member);
    }

    @Override
    public Member updateEntry(Member member) throws UniquenessViolationException, MemberNotFoundException {
        return membersRepository.updateEntry(member);
    }

    @Override
    public Optional<Member> findMemberByUserAndOrg(String orgId, String userId) {
        return membersRepository.findMemberByUserAndOrg(new ObjectId(orgId), new ObjectId(userId));
    }
}
