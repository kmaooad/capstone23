package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class CompetenceRelated {
    private ArrayList<ObjectId> competencesId;

    public ArrayList<ObjectId> getCompetencesId() {
        return competencesId;
    }

    public CompetenceRelated(ArrayList<ObjectId> competencesId) {
        this.competencesId = competencesId;
    }
}
