package edu.kmaooad.capstone23.members.events;

import edu.kmaooad.capstone23.members.dal.Member;
import org.bson.types.ObjectId;

public class MemberRead {
    private ObjectId id;
    private ObjectId orgId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isExpert;
    private boolean isAdmin;

    public MemberRead() {

    }

    public MemberRead(Member member) {
        id = member.id;
        orgId = member.orgId;
        firstName = member.firstName;
        lastName = member.lastName;
        email = member.email;
        isExpert = member.isExpert;
        isAdmin = member.isAdmin;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getOrgId() {
        return orgId;
    }

    public void setOrgId(ObjectId orgId) {
        this.orgId = orgId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isExpert() {
        return isExpert;
    }

    public void setExpert(boolean expert) {
        isExpert = expert;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
