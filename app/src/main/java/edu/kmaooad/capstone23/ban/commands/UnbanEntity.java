package edu.kmaooad.capstone23.ban.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;

public class UnbanEntity {

    @NotNull
    private ObjectId entityId;
    @NotNull
    @Pattern(regexp = "Organization|Department|Member")
    private String entityType;

    public BannedEntityType getEntityType() {
        return BannedEntityType.valueOf(entityType);
    }
    
    public String getEntityTypeName() {
        return entityType;
    }

    public void setEntityTypeName(String entityType) {
        this.entityType = entityType;
    }

    public ObjectId getEntityId() {
        return entityId;
    }

    public void setEntityId(ObjectId entityId) {
        this.entityId = entityId;
    }
}
