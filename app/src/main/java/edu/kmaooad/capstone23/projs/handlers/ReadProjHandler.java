package edu.kmaooad.capstone23.projs.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.projs.dal.Proj;
import edu.kmaooad.capstone23.projs.dal.ProjsRepository;
import jakarta.inject.Inject;

import java.util.List;

public class ReadProjHandler {
    @Inject
    ProjsRepository repository;

    public Result<List<Proj>> readProjects() {
        List<Proj> projects = repository.listAll();
        return new Result<>(projects);
    }
}
