package edu.kmaooad.capstone23.proffesors.commands;

import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import edu.kmaooad.capstone23.proffesors.dal.Proffesor;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public class UpdateProfessor {
    private String firstName;

    private String lastName;

    private String  email;

    private JobPreference preference;

    @NotNull
    private ObjectId professorId;

    public UpdateProfessor(ObjectId professorId) {
        this.professorId = professorId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JobPreference getPreference() {
        return preference;
    }

    public void setPreference(JobPreference preference) {
        this.preference = preference;
    }

    public ObjectId getProfessorId() {
        return professorId;
    }

    public void setProfessorId(ObjectId professorId) {
        this.professorId = professorId;
    }


}
