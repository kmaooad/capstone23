package edu.kmaooad.capstone23.tag.services;

import edu.kmaooad.capstone23.tag.dal.Tag;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface TagService {
    Optional<Tag> findByIdOptional(ObjectId id);

    void delete(Tag tag);

    void persist(Tag tagEntity);

    void update(Tag tag);

    Tag findById(ObjectId id);
}

