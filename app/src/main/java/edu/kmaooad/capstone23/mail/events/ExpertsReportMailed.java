package edu.kmaooad.capstone23.mail.events;

public class ExpertsReportMailed {
    private String recipientEmail;
    private int sizeOfReport;

    public String getRecipientEmail() {
        return recipientEmail;
    }
    public int getSizeOfReport() {return sizeOfReport;}

    public ExpertsReportMailed(String recipientEmail, int size) {
        this.recipientEmail = recipientEmail;
        this.sizeOfReport = size;
    }
}