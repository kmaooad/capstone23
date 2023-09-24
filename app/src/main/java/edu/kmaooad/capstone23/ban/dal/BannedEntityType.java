package edu.kmaooad.capstone23.ban.dal;

public enum BannedEntityType {
    Member("Member"), Department("Department"), Organization("Organization");

    private final String name;

    BannedEntityType(String name) {
        this.name = name;
    }

    public String inferredName() {
        return name;
    }
}
