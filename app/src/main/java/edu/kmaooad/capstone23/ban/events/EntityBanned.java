package edu.kmaooad.capstone23.ban.events;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;

public record EntityBanned(ObjectId id, BannedEntityType entityType) {
}
