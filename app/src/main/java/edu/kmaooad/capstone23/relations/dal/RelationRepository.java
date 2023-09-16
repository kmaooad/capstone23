package edu.kmaooad.capstone23.relations.dal;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@ApplicationScoped
public class RelationRepository {
    @Inject
    MongoClient mongoClient;

    @ConfigProperty(name = "quarkus.mongodb.database")
    String databaseName;

    // TODO handle the result of insertOne
    public Optional<Relation> createRelation(String collectionName1, String collectionName2, Relation relation) {
        var collection = getRelationCollection(collectionName1, collectionName2);
        collection.insertOne(relation);
        return Optional.of(relation);
    }

    // TODO handle the result of deleteOne
    public boolean deleteRelation(String collectionName1, String collectionName2, ObjectId id) {
        var collection = getRelationCollection(collectionName1, collectionName2);
        collection.deleteOne(Filters.eq("_id", id));
        return true;
    }

    private MongoCollection<Relation> getRelationCollection(String collectionName1, String collectionName2) {
        var collectionName = getRelationCollectionName(collectionName1, collectionName2);
        var database = mongoClient.getDatabase(databaseName);
        return database.getCollection(collectionName, Relation.class);
    }

    private String getRelationCollectionName(String collectionName1, String collectionName2) {
        return collectionName1 + "-to-" + collectionName2;
    }
}
