package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Topic;
import org.bson.types.ObjectId;
import java.util.Optional;

public interface TopicService {
  public Optional<Topic> findByIdOptional(ObjectId id);
}
