package edu.kmaooad.capstone23.cvs.events;

public class CVUpdated {
    private final String id;
    private final LocalDateTime dateTimeCreated;
    private final String textInfo;
    private final CV.Status status;
    private final CV.Visibility visibility;
    private final boolean autoAddCompetences;

    public CVUpdated(String id,LocalDateTime dateTimeCreated, String textInfo, CV.Status status,CV.Visibility visibility,boolean autoAddCompetences) {
        this.id = id;
        this.dateTimeCreated = dateTimeCreated;
        this.textInfo = textInfo;
        this.status = status;
        this.visibility = visibility;
        this.autoAddCompetences = autoAddCompetences;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDateTimeCreated() {
        return dateTimeCreated;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public CV.Status getStatus() {
        return status;
    }

    public CV.Visibility getVisibility() {
        return visibility;
    }

    public boolean isAutoAddCompetences() {
        return autoAddCompetences;
    }
}
