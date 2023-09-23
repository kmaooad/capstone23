package edu.kmaooad.capstone23.relations.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

/**
 * Is meant to save any relations to the same
 * collection. Is designed to avoid class-intrusive design.
 * However, leads to relation search degradation.
 */
@ApplicationScoped
public class CollectionMixedRelationRepository implements RelationRepository, PanacheMongoRepository<Relation> {
    @Override
    public Optional<Relation> createRelation(String collectionName1, String collectionName2, Relation relation) {
        persist(relation);
        return Optional.ofNullable(relation);
    }

    @Override
    public Optional<Relation> deleteRelation(String collectionName1, String collectionName2, ObjectId id) {
        return findByIdOptional(id)
                .map(foundRelation -> {
                    delete(foundRelation);
                    return foundRelation;
                });
    }
}
