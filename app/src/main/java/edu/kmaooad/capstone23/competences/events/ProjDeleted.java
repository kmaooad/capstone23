package edu.kmaooad.capstone23.competences.events;
import java.util.List;
public class ProjDeleted {
    private String projId;

    public String getProjId() {
        return projId;
    }

    public ProjDeleted(String projId) {
        this.projId = projId;
    }
}
