package edu.kmaooad.capstone23.relations.dal;

import org.bson.types.ObjectId;

import java.util.Optional;

public interface RelationRepository {
    Optional<Relation> createRelation(String collectionName1, String collectionName2, Relation relation);
    Optional<Relation> deleteRelation(String collectionName1, String collectionName2, ObjectId id);
}
