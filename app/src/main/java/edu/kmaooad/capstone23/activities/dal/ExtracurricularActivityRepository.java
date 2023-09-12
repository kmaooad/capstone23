package edu.kmaooad.capstone23.activities.dal;

import edu.kmaooad.capstone23.departments.dal.Department;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class ExtracurricularActivityRepository implements PanacheMongoRepository<ExtracurricularActivity> {

    public ExtracurricularActivity findByName(String name) {
        return find("name", name).firstResult();
    }

    public ExtracurricularActivity findById(String id) {
        return findById(new ObjectId(id));
    }

    public ExtracurricularActivity insert(ExtracurricularActivity extracurricularActivity){
        persist(extracurricularActivity);
        return extracurricularActivity;
    }

    public void delete(ExtracurricularActivity extracurricularActivity) {
        deleteById(extracurricularActivity.id);
    }

    public void deleteExtracurricularActivity(ExtracurricularActivity extracurricularActivity){
        delete(extracurricularActivity);
    }

    public ExtracurricularActivity modify(ExtracurricularActivity extracurricularActivity) throws IllegalArgumentException {
        if (extracurricularActivity != null && findByIdOptional(extracurricularActivity.id).isEmpty())
            throw new IllegalArgumentException("Extracurricular Activity has unknown id");
        update(extracurricularActivity);
        return extracurricularActivity;
    }
}
