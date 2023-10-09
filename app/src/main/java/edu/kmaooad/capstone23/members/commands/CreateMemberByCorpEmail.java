package edu.kmaooad.capstone23.members.commands;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateMemberByCorpEmail {
    @NotBlank
    @Size(min = 1)
    private String firstName;
    @NotBlank
    @Size(min = 1)
    private String lastName;
    @NotBlank
    @Email
    private String corpEmail;
    @Pattern(regexp = "true|false")
    private String isExpert;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCorpEmail() {
        return corpEmail;
    }

    public void setCorpEmail(String corpEmail) {
        this.corpEmail = corpEmail;
    }

    public String getIsExpert() {
        return isExpert;
    }

    public void setIsExpert(String isExpert) {
        this.isExpert = isExpert;
    }
}
