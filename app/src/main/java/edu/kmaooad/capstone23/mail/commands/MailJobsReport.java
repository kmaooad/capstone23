package edu.kmaooad.capstone23.mail.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MailJobsReport {
    @NotBlank
    @Size(min = 8, max = 50)
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String recipientEmail;

    //blank to all records
    private Integer recordsCount;

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String orgEmail) {
        this.recipientEmail = orgEmail;
    }

    public Integer getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
    }
}
