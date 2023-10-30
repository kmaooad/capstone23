package edu.kmaooad.capstone23.members.dto;

public class OrgDTO {
    private String id;
    private String emailDomain;

    public OrgDTO() {
    }

    public OrgDTO(String id, String emailDomain) {
        this.id = id;
        this.emailDomain = emailDomain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }
}
