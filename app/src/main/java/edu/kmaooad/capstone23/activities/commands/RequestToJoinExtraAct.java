package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RequestToJoinExtraAct {

    @NotBlank
    @Size(min = 2, max = 50)
    private String extraActId;
    public String getExtraActId() {
        return extraActId;
    }

    public void setExtraActId(String extraActId) {
        this.extraActId = extraActId;
    }

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String userName;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
