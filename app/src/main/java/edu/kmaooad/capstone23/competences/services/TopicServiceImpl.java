package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TopicServiceImpl implements TopicService {

    @Inject
    TopicRepository repository;

    @Override
    public void insert(Topic topic) {
        repository.insert(topic);
    }

    @Override
    public Optional<Topic> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public void update(Topic topic) {
        repository.update(topic);
    }

    @Override
    public void delete(Topic topic) {
        repository.delete(topic);
    }

    @Override
    public List<Topic> findByParentId(String name) {
        return repository.findByParentId(name);
    }
}
