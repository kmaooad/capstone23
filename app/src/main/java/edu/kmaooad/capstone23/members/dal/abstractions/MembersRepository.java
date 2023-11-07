package edu.kmaooad.capstone23.members.dal.abstractions;

import edu.kmaooad.capstone23.members.dal.Member;
import edu.kmaooad.capstone23.members.exceptions.MemberNotFoundException;
import edu.kmaooad.capstone23.members.exceptions.UniquenessViolationException;

import java.util.Optional;

public interface MembersRepository {
    Member insert(Member member) throws UniquenessViolationException;

    Member updateEntry(Member member) throws UniquenessViolationException, MemberNotFoundException;

    Optional<Member> findMemberByUserAndOrg(String orgId, String userId);
}
