package edu.kmaooad.capstone23.projs.events;
import java.util.List;
public class ProjUpdated {
    private String projId;
    private String name;
    private String description;
    private List<String> skills;
    private List<String> skillSets;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkillSets() {
        return skillSets;
    }

    public void setSkillSets(List<String> skillSets) {
        this.skillSets = skillSets;
    }

    public ProjUpdated(String projId, String name, String description, List<String> skills, List<String> skillSets) {
        this.projId = projId;
        this.name = name;
        this.description = description;
        this.skills = skills;
        this.skillSets = skillSets;
    }
}