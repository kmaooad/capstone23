package edu.kmaooad.capstone23.students.parser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.Objects;

public class UpdateCSVStudent {

    @NotNull
    private ObjectId id;
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private long DOBTimestamp = 0;
    @Email
    private String email = "";

    public UpdateCSVStudent() {
    }

    public UpdateCSVStudent(ObjectId id, String firstName, String middleName, String lastName, long DOBTimestamp, String email) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.DOBTimestamp = DOBTimestamp;
        this.email = email;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDOBTimestamp(long DOBTimestamp) {
        this.DOBTimestamp = DOBTimestamp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCSVStudent that = (UpdateCSVStudent) o;
        return DOBTimestamp == that.DOBTimestamp &&
                Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, DOBTimestamp, email);
    }
}

