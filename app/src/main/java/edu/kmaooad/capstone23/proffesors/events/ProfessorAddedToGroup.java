package edu.kmaooad.capstone23.proffesors.events;

import org.bson.types.ObjectId;

public class ProfessorAddedToGroup {
    private ObjectId professorId;
    private ObjectId groupId;

    public ProfessorAddedToGroup(ObjectId professorId, ObjectId groupId) {
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
