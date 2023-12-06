package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import java.util.Optional;
import org.bson.types.ObjectId;

public interface ExtracurricularActivityService {
    public ExtracurricularActivity insert(ExtracurricularActivity extracurricularActivity);
    public ExtracurricularActivity findById(String id);
    public ExtracurricularActivity find(String name);
    public ExtracurricularActivity update(ExtracurricularActivity extracurricularActivity);
    public void deleteExtracurricularActivity(ExtracurricularActivity extracurricularActivity);
    public Boolean isExtracurricularActivityRelatedToCourse(ObjectId extracurricularActivityId, ObjectId courseId);
    public Optional<ExtracurricularActivity> findByIdOptional(ObjectId id);
}
