package edu.kmaooad.capstone23.activities.events;

import java.util.List;

public class TagsAddedToExtracurricularActivity {

    private final String activityName;
    private final List<String> tagNames;

    public TagsAddedToExtracurricularActivity(String activityName, List<String> tagNames) {
        this.activityName = activityName;
        this.tagNames = tagNames;
    }

    public String getActivityName() {
        return activityName;
    }

    public List<String> getTagNames() {
        return tagNames;
    }
}
