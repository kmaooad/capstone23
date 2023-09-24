package edu.kmaooad.capstone23.members.dal;

import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class MembersRepository implements PanacheMongoRepository<Member> {

    // NOT USED YET
//    public Member getById(ObjectId id) {
//        return find("id", id).firstResult();
//    }
//
//    public Member getByEmail(String email) {
//        return find("email", email).firstResult();
//    }

    public Member insert(Member member) throws UniquenessViolationException {
        var existingMember = findMemberByEmail(member.email);
        if (existingMember.isPresent())
            throw new UniquenessViolationException("Email is not unique");
        persist(member);
        return member;
    }

    public Member modify(Member member) throws IllegalArgumentException {
        if (member != null && findByIdOptional(member.id).isEmpty())
            throw new IllegalArgumentException("Member has unknown id");
        update(member);
        return member;
    }
  
    public Member updateEntry(Member member) throws UniquenessViolationException {
        var existingMember = findMemberByEmail(member.email);
        if (existingMember.isPresent())
            throw new UniquenessViolationException("Email is not unique");
        update(member);
        return member;
    }

    public Optional<Member> findMemberByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
}