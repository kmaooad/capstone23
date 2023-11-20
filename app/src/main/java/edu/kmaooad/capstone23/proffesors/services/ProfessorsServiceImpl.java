package edu.kmaooad.capstone23.proffesors.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProfessorsServiceImpl implements ProffesorsService{
    @Inject
    private ProffesorsRepository proffesorsRepository;
    @Override
    public Proffesor insert(Proffesor professor) {
        return proffesorsRepository.insert(professor);
    }
}
