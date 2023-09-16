package edu.kmaooad.capstone23.ban.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;

public class BanEntity {

    @NotNull
    private ObjectId entityId;
    @NotNull
    @Pattern(regexp = "Organization|Department|Member")
    private String entityType;

    @NotBlank
    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$")
    private String reason;

    public BannedEntityType getEntityType() {
        return BannedEntityType.valueOf(entityType);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEntityTypeName() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public ObjectId getEntityId() {
        return entityId;
    }

    public void setEntityId(ObjectId entityId) {
        this.entityId = entityId;
    }
}
