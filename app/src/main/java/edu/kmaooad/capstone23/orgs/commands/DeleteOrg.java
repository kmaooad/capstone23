package edu.kmaooad.capstone23.orgs.commands;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotNull;

public class DeleteOrg {
    @NotNull
    private ObjectId orgId;

    public ObjectId getOrgId() {
        return orgId;
    }

    public void setOrgId(ObjectId ordId) {
        this.orgId = ordId;
    }
}
