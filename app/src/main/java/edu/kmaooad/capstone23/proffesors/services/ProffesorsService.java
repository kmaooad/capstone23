package edu.kmaooad.capstone23.proffesors.services;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface ProffesorsService {

    public Proffesor insert(Proffesor proffesor);
    public Optional<Proffesor> findByIdOptional(ObjectId id);
    void update(Proffesor proffesor);
}
