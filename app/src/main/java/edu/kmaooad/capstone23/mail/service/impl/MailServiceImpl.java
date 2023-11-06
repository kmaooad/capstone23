package edu.kmaooad.capstone23.mail.service.impl;

import edu.kmaooad.capstone23.mail.service.MailService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MailServiceImpl implements MailService {
  public void sendEmail(String emailText, String orgEmail) {
    System.out.println("Sending email to " + orgEmail + " with text: " + emailText);
  }
}
