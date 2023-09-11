package edu.kmaooad.capstone23.members.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MembersRepository implements PanacheMongoRepository<Member> {
    public Member insert(Member member) {
        persist(member);
        return member;
    }
}