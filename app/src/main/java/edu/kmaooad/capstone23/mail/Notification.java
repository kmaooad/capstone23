package edu.kmaooad.capstone23.mail;

public class Notification {
    private final String email;
    private final String subject;
    private final String body;

    public Notification(String email, String subject, String body) {
        this.email = email;
        this.subject = subject;
        this.body = body;
    }

    public Notification(String email, String body) {
        this(email, null, body);
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
