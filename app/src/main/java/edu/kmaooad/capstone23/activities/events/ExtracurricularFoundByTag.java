package edu.kmaooad.capstone23.activities.events;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;

import java.util.List;

public class ExtracurricularFoundByTag {
    private final List<ExtracurricularActivity> extracurricularActivities;

    public ExtracurricularFoundByTag(List<ExtracurricularActivity> extracurricularActivities) {
        this.extracurricularActivities = extracurricularActivities;
    }

    public List<ExtracurricularActivity> getExtracurricularActivities() {
        return extracurricularActivities;
    }
}
