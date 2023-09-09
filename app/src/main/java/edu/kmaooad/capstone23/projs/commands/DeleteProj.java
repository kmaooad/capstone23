package edu.kmaooad.capstone23.projs.commands;
import jakarta.validation.constraints.NotBlank;
public class DeleteProj {
    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
