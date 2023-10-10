package edu.kmaooad.capstone23.activities.events;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;

public class SkillToActivityAdded {

    private ExtracurricularActivity activity;

    public SkillToActivityAdded(ExtracurricularActivity activity) {
        this.activity = activity;
    }

    public ExtracurricularActivity getActivity() {
        return activity;
    }
}
