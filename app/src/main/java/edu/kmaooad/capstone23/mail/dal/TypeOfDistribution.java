package edu.kmaooad.capstone23.mail.dal;


public enum TypeOfDistribution {
    STUDENTS("students"), ORGS("organisations"), MEMBERS("members"), PROFESSORS("professors"),
    USERS("users");


    private String value;

    TypeOfDistribution(String v){
        value = v;
    }

    public String getValue(){
        return value;
    }
}
