package edu.kmaooad.capstone23.experts.commands;

import org.bson.types.ObjectId;

public class RemoveExpertFromDepartment {
    private ObjectId departmentId;
    private  ObjectId expertId;

    public ObjectId getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(ObjectId departmentId) {
        this.departmentId = departmentId;
    }

    public ObjectId getExpertId() {
        return expertId;
    }

    public void setExpertId(ObjectId expertId) {
        this.expertId = expertId;
    }
}
