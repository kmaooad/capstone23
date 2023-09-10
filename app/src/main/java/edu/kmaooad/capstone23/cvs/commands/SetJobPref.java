package edu.kmaooad.capstone23.cvs.commands;

import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class SetJobPref {

    @NotNull
    private ObjectId cvId;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String location;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String industry;

    @NotNull
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
