package edu.kmaooad.capstone23.members.dal.Implementations;

import edu.kmaooad.capstone23.members.commands.GetAllMembers;
import edu.kmaooad.capstone23.members.commands.GetAllMembersByOrg;
import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.dal.abstractions.MembersRepository;
import edu.kmaooad.capstone23.members.dto.UserDTO;
import edu.kmaooad.capstone23.members.exceptions.MemberNotFoundException;
import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Page;

import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
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

    @Override
    public Member findById(String objectId) {
        return findById(new ObjectId(objectId));
    }

    @Override
    public void delete(String id) {
        Document query = new Document("_id", id);
        delete(query);
    }

    @Override
    public List<Member> getAllByOrg(GetAllMembersByOrg command) {
        return find("orgId", command.getOrgId())
                .page(Page.of(command.getPage(), command.getSize()))
                .list();
    }

    @Override
    public List<Member> getAll(GetAllMembers command) {
        return findAll().page(Page.of(command.getPage(), command.getSize())).list();
    }

    @Override
    public List<Member> getByUser(UserDTO userDTO) {
        return find("userId", userDTO.getId()).stream().toList();
    }
}
