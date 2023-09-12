package edu.kmaooad.capstone23.experts.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class UpdateExpert {

    @NotBlank
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String expertName;
    @NotNull
    private String id;
    @NotNull
    private String orgId;
    @NotNull
    private String orgName;

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
