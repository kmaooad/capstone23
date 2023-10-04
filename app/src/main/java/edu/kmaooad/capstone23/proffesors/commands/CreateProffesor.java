package edu.kmaooad.capstone23.proffesors.commands;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Set;



    public class CreateProffesor {

        @NotNull
        private String firstName;
private String lastName;

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

        @Email
private String  email;
        public String getName() {
            return firstName;
        }

        public void setName(String name) {
            this.firstName = name;
        }

        public JobPreference getPreference() {
            return preference;
        }

        public void setPreference(JobPreference preference) {
            this.preference = preference;
        }





        private JobPreference preference;



    }


