package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UpdateExtracurricularActivities {
    @NotBlank
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String activityName;
    @NotNull
    private String id;

    @NotNull
    private Date activityDate;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String ActivityName) {
        this.activityName = activityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getActivityDate() {return activityDate;}

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}
