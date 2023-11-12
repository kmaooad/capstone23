package edu.kmaooad.capstone23.experts.commands;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public class AssignExpertToProject {
    @NotNull
    private ObjectId expertId;
    @NotNull
    private ObjectId projectId;

    public ObjectId getProjectId() {
        return projectId;
    }

    public void setProjectId(ObjectId projectId) {
        this.projectId = projectId;
    }

    public ObjectId getExpertId() {
        return expertId;
    }

    public void setExpertId(ObjectId expertId) {
        this.expertId = expertId;
    }
}