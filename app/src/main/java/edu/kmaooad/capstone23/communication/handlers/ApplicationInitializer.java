package edu.kmaooad.capstone23.communication.handlers;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

public class ApplicationInitializer {

    @Inject
    UnitOfWork unitOfWork;

    @PostConstruct
    public void init() {
        unitOfWork.configure();
    }
}
