package edu.kmaooad.capstone23.members.dto;

import org.bson.types.ObjectId;

public class OrgDTO {
    private ObjectId id;
    private String emailDomain;

    public OrgDTO() {
    }

    public OrgDTO(ObjectId id, String emailDomain) {
        this.id = id;
        this.emailDomain = emailDomain;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }
}
