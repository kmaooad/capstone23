package edu.kmaooad.capstone23.students.commands;

import jakarta.validation.constraints.Min;

public class FindStudent {
    @Min(0)
    private int page = 0;
    @Min(1)
    private int size = 20;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getMiddleName() {return middleName;}

    public void setMiddleName(String middleName) {this.middleName = middleName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public int getPage() {return page;}

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
