package edu.kmaooad.capstone23.proffesors.commands;

import org.bson.types.ObjectId;

public class AddProfessorToGroup {
    private ObjectId professorId;
    private ObjectId groupId;

    public AddProfessorToGroup(ObjectId professorId, ObjectId groupId) {
        this.professorId = professorId;
        this.groupId = groupId;
    }

    public ObjectId getProfessorId() {
        return professorId;
    }

    public void setProfessorId(ObjectId professorId) {
        this.professorId = professorId;
    }

    public ObjectId getGroupId() {
        return groupId;
    }

    public void setGroupId(ObjectId groupId) {
        this.groupId = groupId;
    }
}
