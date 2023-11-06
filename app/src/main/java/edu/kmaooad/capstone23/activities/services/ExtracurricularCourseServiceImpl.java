package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import static io.quarkus.mongodb.panache.PanacheMongoEntityBase.*;

@ApplicationScoped
public class ExtracurricularCourseServiceImpl implements ExtracurricularCourseService {
    @Inject
    private ExtracurricularActivityRepository courseRepository;

    @Override
    public ExtracurricularActivity insert(ExtracurricularActivity extracurricularActivity) {
        persist(extracurricularActivity);
        return extracurricularActivity;
    }

    @Override
    public ExtracurricularActivity findById(String id) {
        return findById(String.valueOf(new ObjectId(id)));
    }

    @Override
    public void deleteExtracurricularActivity(ExtracurricularActivity extracurricularActivity) {
        delete(extracurricularActivity);
    }

    @Override
    public ExtracurricularActivity update(ExtracurricularActivity extracurricularActivity) {
        persistOrUpdate(extracurricularActivity);
        return  extracurricularActivity;
    }

    public void delete(ExtracurricularActivity extracurricularActivity) {
        deleteById(extracurricularActivity.id);
    }

}
