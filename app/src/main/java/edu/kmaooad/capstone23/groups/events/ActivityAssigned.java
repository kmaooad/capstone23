package edu.kmaooad.capstone23.groups.events;

public class ActivityAssigned {
    private final String groupId;
    private final String activityId;

    public ActivityAssigned(String groupId, String activityId) {
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
