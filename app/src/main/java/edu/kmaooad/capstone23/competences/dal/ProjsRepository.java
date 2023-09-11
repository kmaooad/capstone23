package edu.kmaooad.capstone23.competences.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjsRepository implements PanacheMongoRepository<Project> {

    public Project findByName(String name) {
        return find("name", name).firstResult();
    }

    public Project insert(Project project) {
        persist(project);
        return project;
    }
}
