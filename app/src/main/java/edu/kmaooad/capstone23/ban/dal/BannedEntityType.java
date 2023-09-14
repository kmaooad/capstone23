package edu.kmaooad.capstone23.ban.dal;

public enum BannedEntityType {
    User("user"), Department("department"), Organization("organization");

    public final String text;

    BannedEntityType(String text) {
        this.text = text;
    }

}
