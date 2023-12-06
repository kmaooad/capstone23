package edu.kmaooad.capstone23.proffesors.events;

import org.bson.types.ObjectId;

public class ProfessorDeleted {
    private ObjectId professorId;
    private boolean professorExisted;


    public ProfessorDeleted(ObjectId professorId, boolean professorExisted) {
        this.professorId = professorId;
        this.professorExisted = professorExisted;
    }

    public ObjectId getProfessorId() {
        return professorId;
    }

    public void setProfessorId(ObjectId professorId) {
        this.professorId = professorId;
    }

    public boolean isProfessorExisted() {
        return professorExisted;
    }

    public void setProfessorExisted(boolean professorExisted) {
        this.professorExisted = professorExisted;
    }
}
