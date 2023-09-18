package edu.kmaooad.capstone23.orgs.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MailService {

    public void sendEmail(String emailText, String orgEmail) {
        System.out.println("Sending email to " + orgEmail + " with text: " + emailText);
    }
}
