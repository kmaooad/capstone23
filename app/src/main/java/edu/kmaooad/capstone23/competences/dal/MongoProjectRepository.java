package edu.kmaooad.capstone23.competences.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class MongoProjectRepository implements PanacheMongoRepository<Project>, ProjectsRepository {
    public Project findByName(String name) {
        return find("name", name).firstResult();
    }

    public Project insert(Project project) {
        persist(project);
        return project;
    }

    @Override
    public Optional<Project> findByIdMaybe(ObjectId id) {
        return this.findByIdOptional(id);
    }

    @Override
    public Project insertProject(Project project) {
        return insert(project);
    }

    @Override
    public Project findProjectById(String id) {
        ObjectId objectId = new ObjectId(id);
        return findById(objectId);
    }

    @Override
    public void updateProject(Project updatedProject) {
        update(updatedProject);
    }

    @Override
    public void deleteProject(Project entity) {
        delete(entity);
    }
}
