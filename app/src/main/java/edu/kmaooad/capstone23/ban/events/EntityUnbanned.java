package edu.kmaooad.capstone23.ban.events;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.ban.dal.BannedEntityType;

public record EntityUnbanned(ObjectId id, BannedEntityType entityType) {
}
