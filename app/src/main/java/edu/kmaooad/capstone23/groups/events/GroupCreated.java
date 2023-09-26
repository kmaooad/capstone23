package edu.kmaooad.capstone23.groups.events;

public class GroupCreated {
    private final String groupId;

    public GroupCreated(String groupId) { this.groupId = groupId; }

    public String getGroupId() {
        return groupId;
    }
}
