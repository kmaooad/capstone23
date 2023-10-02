package edu.kmaooad.capstone23.cvs.commands;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;

public class ReadCV {

    private CV.Status status;

    private String industry;

    private String location;

    public String textInfo;

    private JobPreference.Category category;

    public CV.Status getStatus() {
        return status;
    }

    public void setStatus(CV.Status status) {
        this.status = status;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public JobPreference.Category getCategory() {
        return category;
    }

    public void setCategory(JobPreference.Category category) {
        this.category = category;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }


}
