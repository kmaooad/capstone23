package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;

import org.bson.types.ObjectId;

@ApplicationScoped
public class ExtracurricularActivityImpl implements ExtracurricularActivityService {
    @Inject
    private ExtracurricularActivityRepository extracurricularActivityRepository;

    @Inject CourseService courseService;

    @Override
    public ExtracurricularActivity insert(ExtracurricularActivity extracurricularActivity) {
        return extracurricularActivityRepository.insert(extracurricularActivity);
    }

    @Override
    public ExtracurricularActivity findById(String id) {
        return extracurricularActivityRepository.findById(id);
    }

    @Override
    public Optional<ExtracurricularActivity> findByIdOptional(ObjectId id) {
        return extracurricularActivityRepository.findByIdOptional(id);
    }

    public ExtracurricularActivity find(String extracurricularActivityName) {
        return extracurricularActivityRepository.find("extracurricularActivityName", extracurricularActivityName).firstResult();
    }

    @Override
    public ExtracurricularActivity update(ExtracurricularActivity extracurricularActivity) {
        extracurricularActivityRepository.update(extracurricularActivity);
        return extracurricularActivity;
    }

    @Override
    public void deleteExtracurricularActivity(ExtracurricularActivity extracurricularActivity) {
        extracurricularActivityRepository.delete(extracurricularActivity);
    }

    @Override
    public Boolean isExtracurricularActivityRelatedToCourse(ObjectId extracurricularActivityId, ObjectId courseId) {
        Optional<ExtracurricularActivity> extActivity = this.findByIdOptional(extracurricularActivityId);
        Optional<Course> course = courseService.findById(courseId.toHexString());

        return !(course.isEmpty() && extActivity.isEmpty());
    }
}
