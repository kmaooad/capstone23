package edu.kmaooad.capstone23.ban.commands;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.bson.types.ObjectId;

public class IsEntityBanned {

    @NotNull
    private ObjectId entityId;

    @NotNull
    @Pattern(regexp = "Organization|Department|Member")
    private String entityType;


    public IsEntityBanned() {}

    public IsEntityBanned(ObjectId entityId, String entityType) {
        this.entityId = entityId;
        this.entityType = entityType;
    }


    public ObjectId getEntityId() {
        return entityId;
    }

    public void setEntityId(ObjectId entityId) {
        this.entityId = entityId;
    }

    public BannedEntityType getEntityType() {
        return BannedEntityType.valueOf(entityType);
    }

    public String getEntityTypeName() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
