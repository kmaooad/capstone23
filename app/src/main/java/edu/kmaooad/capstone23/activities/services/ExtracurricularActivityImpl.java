package edu.kmaooad.capstone23.activities.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ExtracurricularActivityImpl implements ExtracurricularActivityService {
    @Inject
    private ExtracurricularActivityRepository extracurricularActivityRepository;
    @Override
    public ExtracurricularActivity insert(ExtracurricularActivity extracurricularActivity) {
        return extracurricularActivityRepository.insert(extracurricularActivity);
    }

    @Override
    public ExtracurricularActivity findById(String id) {
        return extracurricularActivityRepository.findById(id);
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
}
