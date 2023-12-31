package edu.kmaooad.capstone23.competences.events;

public class TopicUpdated {
    private final String id;
    private final String name;
    private final String parentId;

    public TopicUpdated(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }
}
