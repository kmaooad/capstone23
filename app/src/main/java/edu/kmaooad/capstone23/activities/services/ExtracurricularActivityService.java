package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ExtracurricularActivityService {
    public ExtracurricularActivity insert(ExtracurricularActivity extracurricularActivity);
    public ExtracurricularActivity findById(String id);
    public ExtracurricularActivity find(String name);
    public ExtracurricularActivity update(ExtracurricularActivity extracurricularActivity);
    public void deleteExtracurricularActivity(ExtracurricularActivity extracurricularActivity);
    public Optional<ExtracurricularActivity> findByIdOptional(ObjectId id);
}
