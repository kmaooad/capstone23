package edu.kmaooad.capstone23.messages.utils;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailValidator {
    //private Regex

    public boolean isValid(String value) {
       return value.matches(
               "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"
       );
    }
}
