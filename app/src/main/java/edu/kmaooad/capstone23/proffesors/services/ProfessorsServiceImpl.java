package edu.kmaooad.capstone23.proffesors.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class ProfessorsServiceImpl implements ProffesorsService{
    @Inject
    private ProffesorsRepository proffesorsRepository;
    @Override
    public Proffesor insert(Proffesor professor) {
        return proffesorsRepository.insert(professor);
    }

    public Optional<Proffesor> findByIdOptional(ObjectId id) {
        return proffesorsRepository.findByIdOptional(id);
    }

    @Override
    public void update(Proffesor proffesor) {
        proffesorsRepository.update(proffesor);
    }

}
