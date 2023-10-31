package edu.kmaooad.capstone23.members.dal;

import edu.kmaooad.capstone23.members.exceptions.MemberNotFoundException;
import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class MembersRepository implements PanacheMongoRepository<Member> {
    public Member insert(Member member) throws UniquenessViolationException {
        var existingMember = findMemberByUserAndOrg(member.orgId, member.userId);
        if (existingMember.isPresent())
            throw new UniquenessViolationException("Entry fot the given org and user id already exists");
        persist(member);
        return member;
    }

    public Member updateEntry(Member member) throws UniquenessViolationException, MemberNotFoundException {
        var existingMember = findByIdOptional(member.id);
        var existingUserAndOrgEntry = findMemberByUserAndOrg(member.orgId, member.userId);
        if (existingMember.isPresent()
                && existingUserAndOrgEntry.isPresent()
                && !existingUserAndOrgEntry.get().id.equals(existingMember.get().id))
            throw new UniquenessViolationException("Entry for the given org and user id already exists");
        if (existingMember.isEmpty())
            throw new MemberNotFoundException("Member was not found");
        update(member);
        return member;
    }

    public Optional<Member> findMemberByUserAndOrg(ObjectId orgId, ObjectId userId) {
        Document query = new Document("orgId", orgId).append("userId", userId);
        return find(query).firstResultOptional();
    }
}