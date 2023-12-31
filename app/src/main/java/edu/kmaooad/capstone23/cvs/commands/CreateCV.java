package edu.kmaooad.capstone23.cvs.commands;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateCV {

    @NotNull
    @PastOrPresent
    private LocalDateTime dateTimeCreated;

    private String textInfo;

    @NotNull
    private CV.Status status;

    @NotNull
    private CV.Visibility visibility;

    private boolean autoAddCompetences;

    private JobPreference preference;

    private Set<ObjectId> skills;

    private String studentId;

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

    public JobPreference getPreference() {
        return preference;
    }

    public void setPreference(JobPreference preference) {
        this.preference = preference;
    }

    public Set<ObjectId> getSkills() {
        return skills;
    }

    public void setSkills(Set<ObjectId> skills) {
        this.skills = skills;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
