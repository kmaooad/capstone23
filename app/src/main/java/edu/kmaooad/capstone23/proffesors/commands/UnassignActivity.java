package edu.kmaooad.capstone23.proffesors.commands;

import org.bson.types.ObjectId;

public class UnassignActivity {
    private ObjectId professor;
    private ObjectId activity;

    public ObjectId getProfessor() {
        return professor;
    }

    public void setProfessor(ObjectId professor) {
        this.professor = professor;
    }

    public ObjectId getActivity() {
        return activity;
    }

    public void setActivity(ObjectId activity) {
        this.activity = activity;
    }
}
