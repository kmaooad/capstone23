package edu.kmaooad.capstone23.tag.dal;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.Pattern;

@MongoEntity(collection = "tags")
public class Tag extends PanacheMongoEntity {
    String tagName;
}
