package edu.kmaooad.capstone23.proffesors.commands;

import org.bson.types.ObjectId;

public class DeleteProfessor {
    private ObjectId professorId;


    public DeleteProfessor(ObjectId professorId) {
        this.professorId = professorId;
    }

    public ObjectId getProfessorId() {
        return professorId;
    }

    public void setProfessorId(ObjectId professorId) {
        this.professorId = professorId;
    }
}
