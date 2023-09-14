package edu.kmaooad.capstone23.members.dal;

import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MembersRepository implements PanacheMongoRepository<Member> {
    public Member insert(Member member) {
        var existingMember = find("email", member.email).firstResultOptional();
        if (existingMember.isPresent())
            throw new UniquenessViolationException("Email is not unique");
        persist(member);
        return member;
    }
}