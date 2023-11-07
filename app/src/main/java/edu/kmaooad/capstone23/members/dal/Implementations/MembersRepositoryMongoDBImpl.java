package edu.kmaooad.capstone23.members.dal.Implementations;

import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.members.exceptions.MemberNotFoundException;
import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

import java.util.Optional;

@ApplicationScoped
public class MembersRepositoryMongoDBImpl implements MembersRepository, PanacheMongoRepository<Member> {

    @Override
    public Member insert(Member member) throws UniquenessViolationException {
        var existingMember = findMemberByUserAndOrg(member.orgId.toString(), member.userId.toString());
        if (existingMember.isPresent())
            throw new UniquenessViolationException("Entry fot the given org and user id already exists");
        persist(member);
        return member;
    }

    @Override
    public Member updateEntry(Member member) throws UniquenessViolationException, MemberNotFoundException {
        var existingMember = findByIdOptional(member.id);
        var existingUserAndOrgEntry = findMemberByUserAndOrg(member.orgId.toString(), member.userId.toString());
        if (existingMember.isPresent()
                && existingUserAndOrgEntry.isPresent()
                && !existingUserAndOrgEntry.get().id.equals(existingMember.get().id))
            throw new UniquenessViolationException("Entry for the given org and user id already exists");
        if (existingMember.isEmpty())
            throw new MemberNotFoundException("Member was not found");
        update(member);
        return member;
    }

    @Override
    public Optional<Member> findMemberByUserAndOrg(String orgId, String userId) {
        Document query = new Document("orgId", orgId).append("userId", userId);
        return find(query).firstResultOptional();
    }
}
