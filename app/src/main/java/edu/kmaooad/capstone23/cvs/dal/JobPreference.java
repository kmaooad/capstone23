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

    public JobPreference(String location, String industry, Category category) {
        this.location = location;
        this.industry = industry;
        this.category = category;
    }

    public JobPreference() {
    }
}
