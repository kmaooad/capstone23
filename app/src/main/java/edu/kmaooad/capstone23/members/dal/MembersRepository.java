package edu.kmaooad.capstone23.members.dal;

import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class MembersRepository implements PanacheMongoRepository<Member> {
    public Member insert(Member member) throws UniquenessViolationException {
        var existingMember = findMemberByEmail(member.email);
        if (existingMember.isPresent())
            throw new UniquenessViolationException("Email is not unique");
        persist(member);
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