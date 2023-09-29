package edu.kmaooad.capstone23.groups.events;

public class GroupDeleted {

    private final String groupId;

    public GroupDeleted(String groupId) { this.groupId = groupId; }

    public String getGroupId() {
        return groupId;
    }
}
