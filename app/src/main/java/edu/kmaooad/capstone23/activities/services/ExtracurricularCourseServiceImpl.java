package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static io.quarkus.mongodb.panache.PanacheMongoEntityBase.persist;

@ApplicationScoped
public class ExtracurricularCourseServiceImpl implements ExtracurricularCourseService {
    @Inject
    private ExtracurricularActivityRepository courseRepository;

    @Override
    public ExtracurricularActivity insert(ExtracurricularActivity extracurricularActivity) {
        persist(extracurricularActivity);
        return extracurricularActivity;
    }
}
