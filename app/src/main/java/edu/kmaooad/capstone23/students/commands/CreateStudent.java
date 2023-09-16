package edu.kmaooad.capstone23.students.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.jboss.resteasy.reactive.RestForm;

import java.io.File;

public class CreateStudent {

    @RestForm
    public File content;

}
