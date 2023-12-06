package edu.kmaooad.capstone23.proffesors.services;

import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import edu.kmaooad.capstone23.proffesors.dal.ProffesorsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class ProffesorsServiceImpl implements ProffesorsService {
    @Inject
    ProffesorsRepository proffesorsRepository;

    @Override
    public Proffesor insert(Proffesor pr) {
        return proffesorsRepository.insert(pr);
    }

    @Override
    public Optional<Proffesor> findByIdOptional(ObjectId professor) {
        return proffesorsRepository.findByIdOptional(professor);
    }

    @Override
    public void update(Proffesor proffesor) {
        proffesorsRepository.update(proffesor);
    }
}
