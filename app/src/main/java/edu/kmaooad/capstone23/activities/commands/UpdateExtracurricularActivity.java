package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UpdateExtracurricularActivity {
    @NotBlank
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String extracurricularActivityName;
    @NotNull
    private String id;

    @NotNull
    private Date extracurricularActivityDate;

    public String getExtracurricularActivityName() {
        return extracurricularActivityName;
    }

    public void setActivityName(String ExtracurricularActivityName) {
        this.extracurricularActivityName = ExtracurricularActivityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExtracurricularActivityDate() {return extracurricularActivityDate;}

    public void setExtracurricularActivityDate(Date activityDate) {
        this.extracurricularActivityDate = activityDate;
    }
}
