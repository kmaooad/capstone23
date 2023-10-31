package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {

    void insert(Topic topic);

    Optional<Topic> findById(String id);

    void update(Topic topic);

    void delete(Topic topic);

    List<Topic> findByParentId(String id);
}
