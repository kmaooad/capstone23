package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class CreateExtracurricularActivity {
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String extracurricularActivityName;
    private Date extracurricularActivityDate;

    public String getExtracurricularActivityName() {
        return extracurricularActivityName;
    }

    public void setExtracurricularActivityName(String activityName) {
        this.extracurricularActivityName = activityName;
    }

    public Date getExtracurricularActivityDate() {
        return extracurricularActivityDate;
    }

    public void setExtracurricularActivityDate(Date activityDate) {
        this.extracurricularActivityDate = activityDate;
    }
}
