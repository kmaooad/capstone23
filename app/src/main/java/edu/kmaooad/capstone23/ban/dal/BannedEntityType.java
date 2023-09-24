package edu.kmaooad.capstone23.ban.dal;

public enum BannedEntityType {
    Member, Department, Organization;

    public String inferredName() {
        return switch (this) {
            case Member -> "Member";
            case Department -> "Department";
            case Organization -> "Organization";
        };
    }
}
