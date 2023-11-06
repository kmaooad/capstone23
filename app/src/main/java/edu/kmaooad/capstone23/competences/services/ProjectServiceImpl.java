package edu.kmaooad.capstone23.competences.services;

import edu.kmaooad.capstone23.competences.dal.MongoProjectRepository;
import edu.kmaooad.capstone23.competences.dal.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class ProjectServiceImpl implements ProjectService {

    @Inject
    MongoProjectRepository repository;

    @Override
    public Project findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Project insert(Project project) {
        return repository.insert(project);
    }

    @Override
    public Project findById(String id) {
        return repository.findById(new ObjectId(id));
    }

    @Override
    public void update(Project project) {
        repository.update(project);
    }

    @Override
    public void delete(Project project) {
        repository.delete(project);
    }

    @Override
    public Optional<Project> findByIdOptional(String id) {
        return repository.findByIdOptional(new ObjectId(id));
    }
}
