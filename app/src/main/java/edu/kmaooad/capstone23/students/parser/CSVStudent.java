package edu.kmaooad.capstone23.students.parser;

public class CSVStudent {
    private String firstName;
    private String middleName;
    private String lastName;
    private long DOBTimestamp;
    private String email;

    public CSVStudent(String firstName, String middleName, String lastName, long DOBTimestamp, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.DOBTimestamp = DOBTimestamp;
        this.email = email;
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
}

