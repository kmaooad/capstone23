package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateActivity {
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String activityName;

    public String getActivityName() {
        return activityName;
    }

    public void setActivitytName(String activityName) {
        this.activityName = activityName;
    }
}
