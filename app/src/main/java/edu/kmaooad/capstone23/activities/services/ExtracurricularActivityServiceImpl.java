package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;

import org.bson.types.ObjectId;

@ApplicationScoped
public class ExtracurricularActivityServiceImpl implements ExtracurricularActivityService {
    @Inject
    private CourseService courseService;
    @Inject
    private ExtracurricularActivityRepository repo;

    @Override
    public ExtracurricularActivity insert(ExtracurricularActivity extracurricularActivity) {
        return null;
    }

    @Override
    public ExtracurricularActivity findById(String id) {
        return null;
    }

    @Override
    public ExtracurricularActivity find(String name) {
        return null;
    }

    @Override
    public ExtracurricularActivity update(ExtracurricularActivity extracurricularActivity) {
        return null;
    }

    @Override
    public void deleteExtracurricularActivity(ExtracurricularActivity extracurricularActivity) {

    }

    public Optional<ExtracurricularActivity> findByIdOptional(ObjectId id) {
        return repo.findByIdOptional(id);
    }

    public Boolean isExtracurricularActivityRelatedToCourse(ObjectId extracurricularActivityId, ObjectId courseId) {
        Optional<ExtracurricularActivity> extActivity = this.findByIdOptional(extracurricularActivityId);
        Optional<Course> course = courseService.findById(courseId.toHexString());

        return !(course.isEmpty() && extActivity.isEmpty());
    }

}
