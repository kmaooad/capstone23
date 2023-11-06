package edu.kmaooad.capstone23.competences.services.impl;

import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import edu.kmaooad.capstone23.competences.services.TopicService;
import jakarta.inject.Inject;
import java.util.Optional;

import org.bson.types.ObjectId;

public class TopicServiceImpl implements TopicService {
  @Inject
  private TopicRepository repo;

  @Override
  public Optional<Topic> findByIdOptional(ObjectId id) {
    return repo.findByIdOptional(id);
  }
}
