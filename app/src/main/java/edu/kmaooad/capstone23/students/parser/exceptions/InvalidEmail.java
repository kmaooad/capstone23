package edu.kmaooad.capstone23.students.parser.exceptions;

public class InvalidEmail extends Exception {
    public InvalidEmail(String message, String email) {
        super(message + email);
    }
}
