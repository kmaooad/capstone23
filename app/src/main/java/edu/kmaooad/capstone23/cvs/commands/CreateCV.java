package edu.kmaooad.capstone23.cvs.commands;

import edu.kmaooad.capstone23.cvs.dal.CV;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CreateCV {

    @NotNull
    @PastOrPresent
    private LocalDateTime dateTimeCreated;

    @Size(min = 10, max = 500)
    private String textInfo;

    @NotNull
    private CV.Status status;

    @NotNull
    private CV.Visibility visibility;

    private boolean autoAddCompetences;

    public LocalDateTime getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }

    public CV.Status getStatus() {
        return status;
    }

    public void setStatus(CV.Status status) {
        this.status = status;
    }

    public CV.Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(CV.Visibility visibility) {
        this.visibility = visibility;
    }

    public boolean isAutoAddCompetences() {
        return autoAddCompetences;
    }

    public void setAutoAddCompetences(boolean autoAddCompetences) {
        this.autoAddCompetences = autoAddCompetences;
    }

}
