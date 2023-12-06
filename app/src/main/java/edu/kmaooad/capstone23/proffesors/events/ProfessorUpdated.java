package edu.kmaooad.capstone23.proffesors.events;

import org.bson.types.ObjectId;

public class ProfessorUpdated {
    private ObjectId professorId;

    public ProfessorUpdated(ObjectId professorId) {
        this.professorId = professorId;
    }

    public ObjectId getProfessorId() {
        return professorId;
    }

    public void setProfessorId(ObjectId professorId) {
        this.professorId = professorId;
    }
}
