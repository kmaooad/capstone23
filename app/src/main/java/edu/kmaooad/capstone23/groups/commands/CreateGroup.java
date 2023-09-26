package edu.kmaooad.capstone23.groups.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class CreateGroup {
    @NotBlank
    @Size(min = 1, max = 50)
    private String groupName;
    @NotBlank
    private String templateId;

    private List<ObjectId> activitiesId;
    public CreateGroup(){

        this.activitiesId = new ArrayList<>();
    }

    public String getGroupName() {
        return groupName;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<ObjectId> getActivitiesId() {
        return activitiesId;
    }
}
