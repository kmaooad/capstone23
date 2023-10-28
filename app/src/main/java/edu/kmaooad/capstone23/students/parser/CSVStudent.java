package edu.kmaooad.capstone23.students.parser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CSVStudent(String firstName,
                         String middleName,
                         String lastName,
                         long DOBTimestamp,
                         @NotBlank @Email String email) {

}
