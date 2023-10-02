package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class AddTagsToExtracurricularActivity {
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9\s]*$")
    private String extracurricularActivityName;
    @NotEmpty
    private List<String> tagIds;

    public String getExtracurricularActivityName() {
        return extracurricularActivityName;
    }

    public void setExtracurricularActivityName(String extracurricularActivityName) {
        this.extracurricularActivityName = extracurricularActivityName;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }
}