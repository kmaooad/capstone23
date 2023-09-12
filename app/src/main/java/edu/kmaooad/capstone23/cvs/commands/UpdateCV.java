package edu.kmaooad.capstone23.cvs.commands;

import edu.kmaooad.capstone23.cvs.dal.CV;
import jakarta.validation.constraints.NotNull;

public class UpdateCV {

    @NotNull
    private String cvId;

    private CV.Status status;

    private CV.Visibility visibility;

    private String textInfo;

    public String getCvId() {
        return cvId;
    }

    public void setCvId(String cvId) {
        this.cvId = cvId;
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

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }
}
