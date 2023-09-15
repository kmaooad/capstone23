package edu.kmaooad.capstone23.students.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateStudent {

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]*")
    private String middleName;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;

    private long DOBTimestamp;

    @NotBlank
    @Email
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getDOBTimestamp() {
        return DOBTimestamp;
    }

    public void setDOBTimestamp(long DOBTimestamp) {
        this.DOBTimestamp = DOBTimestamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
