package edu.kmaooad.capstone23.orgs.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RequestToJoinOrg {

    @NotBlank
    @Size(min = 2, max = 50)
    private String orgId;
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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
