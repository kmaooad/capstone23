package edu.kmaooad.capstone23.students.parser;

import org.bson.types.ObjectId;

import java.util.Objects;

public class UpdateCSVStudent {

    private ObjectId id;
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private long DOBTimestamp = 0;
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

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

