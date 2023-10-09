package edu.kmaooad.capstone23.activities.events;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;

public class SkillFromActivityRemoved {

    private ExtracurricularActivity activity;

    public SkillFromActivityRemoved(ExtracurricularActivity activity) {
        this.activity = activity;
    }

    public ExtracurricularActivity getActivity() {
        return activity;
    }

}
