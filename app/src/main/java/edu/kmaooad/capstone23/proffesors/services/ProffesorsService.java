package edu.kmaooad.capstone23.proffesors.services;

import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface ProffesorsService {
    Proffesor insert(Proffesor pr);

    Optional<Proffesor> findByIdOptional(ObjectId professor);

    void update(Proffesor proffesor);
}
