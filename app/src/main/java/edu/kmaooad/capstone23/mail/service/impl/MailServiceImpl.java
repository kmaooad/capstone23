package edu.kmaooad.capstone23.mail.service.impl;

import edu.kmaooad.capstone23.mail.service.MailService;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MailServiceImpl implements MailService {
  @Inject
  Mailer mailer;

  public void sendEmail(String emailText, String orgEmail) {
    Mail mail = new Mail()
        .addTo(orgEmail)
        .setText(emailText);

    mailer.send(mail);
  }
}
