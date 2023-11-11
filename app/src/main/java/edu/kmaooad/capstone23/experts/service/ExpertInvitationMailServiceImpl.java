package edu.kmaooad.capstone23.experts.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExpertInvitationMailServiceImpl implements ExpertInvitationMailService {
    private static final String INVITATION_SUBJECT = "Capstone23 invitation";
    @Inject
    Mailer mailer;

    @Override
    public void sendInvitationLink(String email, String invitationBody) {
        mailer.send(Mail.withText(email, INVITATION_SUBJECT, invitationBody));
    }

}
