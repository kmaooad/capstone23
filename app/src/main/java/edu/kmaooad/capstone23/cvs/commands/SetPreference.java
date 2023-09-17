package edu.kmaooad.capstone23.cvs.commands;

import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public class SetPreference {

    @NotNull
    private ObjectId cvId;

    private String location;

    private String industry;

    private JobPreference.Category category;

    public ObjectId getCvId() {
        return cvId;
    }

    public void setCvId(ObjectId cvId) {
        this.cvId = cvId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public JobPreference.Category getCategory() {
        return category;
    }

    public void setCategory(JobPreference.Category category) {
        this.category = category;
    }
}
