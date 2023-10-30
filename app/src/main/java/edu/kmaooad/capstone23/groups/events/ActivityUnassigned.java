package edu.kmaooad.capstone23.groups.events;

public class ActivityUnassigned {
    private final String groupId;
    private final String activityId;

    public ActivityUnassigned(String groupId, String activityId) {
        this.groupId = groupId;
        this.activityId = activityId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getActivitiesId() {
        return activityId;
    }
}
