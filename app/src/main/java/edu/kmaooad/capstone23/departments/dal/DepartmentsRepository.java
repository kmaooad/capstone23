package edu.kmaooad.capstone23.departments.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartmentsRepository implements PanacheMongoRepository<Departments> {

    public Departments findByName(String name) {
        return find("name", name).firstResult();
    }

    public Departments insert(Departments departments){
        persist(departments);
        return departments;
    }
}