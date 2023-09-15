package edu.kmaooad.capstone23.members.dal;

import edu.kmaooad.capstone23.experts.dal.Expert;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class MembersRepository implements PanacheMongoRepository<Member> {

    public Member getById(ObjectId id) {
        return find("id", id).firstResult();
    }

    public Member getByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Member insert(Member member) {
        persist(member);
        return member;
    }

    public Member modify(Member member) throws IllegalArgumentException {
        if (member != null && findByIdOptional(member.id).isEmpty())
            throw new IllegalArgumentException("Member has unknown id");
        update(member);
        return member;
    }
}