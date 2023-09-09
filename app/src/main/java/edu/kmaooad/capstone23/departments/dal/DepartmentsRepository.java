package edu.kmaooad.capstone23.departments.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartmentsRepository implements PanacheMongoRepository<Department> {

    public Department findByName(String name) {
        return find("name", name).firstResult();
    }


    public Department findById(String id) {
        return find("id", id).firstResult();
    }

    public Department insert(Department departments){
        persist(departments);
        return departments;
    }
}