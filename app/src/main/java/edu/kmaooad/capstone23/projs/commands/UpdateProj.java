package edu.kmaooad.capstone23.projs.commands;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

public class UpdateProj {

    private String id;

    @NotBlank
    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$")
    private String name;

    private String description;

    private List<String> skills;

    private List<String> skillSets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
