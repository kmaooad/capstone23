package edu.kmaooad.capstone23.experts.events;

public class InvitationLinkCreated {
    private String invitationLink;

    public InvitationLinkCreated(String invitationLink) {
        this.invitationLink = invitationLink;
    }

    public String getInvitationLink() {
        return invitationLink;
    }

    public void setInvitationLink(String invitationLink) {
        this.invitationLink = invitationLink;
    }
}
