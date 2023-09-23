package edu.kmaooad.capstone23.members.events;

import edu.kmaooad.capstone23.members.dal.Member;

import java.util.List;

public class MembersListed {
    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
