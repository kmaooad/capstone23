package edu.kmaooad.capstone23.experts.commands;

import edu.kmaooad.capstone23.orgs.dal.Org;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

public class UpdateExpert {

    @NotBlank
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String expertName;

    private ObjectId org;
    @NotNull
    private ObjectId id;

    public String getExpertName() {
        return expertName;
    }

    public ObjectId getOrg() {
        return org;
    }

    public void setOrg(ObjectId org) {
        this.org = org;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }
}
