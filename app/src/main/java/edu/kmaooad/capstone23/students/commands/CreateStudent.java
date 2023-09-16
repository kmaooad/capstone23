package edu.kmaooad.capstone23.students.commands;

import org.jboss.resteasy.reactive.RestForm;

import java.io.File;

public class CreateStudent {
    @RestForm
    public File csvFile;
}
