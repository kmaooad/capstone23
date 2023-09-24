package edu.kmaooad.capstone23.students.parser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class CSVStudent {
    private String firstName;
    private String middleName;
    private String lastName;
    private long DOBTimestamp;
    @NotBlank
    @Email
    private String email;

    public CSVStudent(String firstName, String middleName, String lastName, long DOBTimestamp, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.DOBTimestamp = DOBTimestamp;
        this.email = email;
    }

    public CSVStudent() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getDOBTimestamp() {
        return DOBTimestamp;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CSVStudent that)) return false;
        return DOBTimestamp == that.DOBTimestamp &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, DOBTimestamp, email);
    }
}

