package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import java.util.Optional;
import org.bson.types.ObjectId;


public interface ExtracurricularActivityService {
    public Optional<ExtracurricularActivity> findByIdOptional(ObjectId id);
    
    Boolean isExtracurricularActivityRelatedToCourse(ObjectId extracurricularActivityId, ObjectId courseId);
}
