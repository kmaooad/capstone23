package edu.kmaooad.capstone23.cvs.dal;

public class JobPreference {

    public String location;
    public String industry;
    public Category category;

//    private List<Competence> competences;

    public enum Category {
        FULL_TIME,
        PART_TIME
    }

}
