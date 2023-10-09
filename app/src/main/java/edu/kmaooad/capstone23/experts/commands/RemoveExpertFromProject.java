package edu.kmaooad.capstone23.experts.commands;

import org.bson.types.ObjectId;

public class RemoveExpertFromProject {
    private ObjectId expertId;
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
