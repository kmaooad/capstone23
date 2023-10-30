package edu.kmaooad.capstone23.ban.commands;

import jakarta.validation.constraints.NotNull;

public class IsEntityBannedV2 {


    @NotNull
    private String entityId;

    @NotNull
    private String entityType;

    public final static String ORGANIZATION_BAN_ENTITY_TYPE = "Organization";
    public final static String DEPARTMENT_BAN_ENTITY_TYPE = "Department";
    public final static String MEMBER_BAN_ENTITY_TYPE = "Member";


    public IsEntityBannedV2() {
    }


    public IsEntityBannedV2(String entityId, String entityType) {
        this.entityId = entityId;
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
