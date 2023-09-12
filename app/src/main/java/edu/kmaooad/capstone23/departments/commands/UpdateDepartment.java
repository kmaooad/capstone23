package edu.kmaooad.capstone23.departments.commands;

import io.smallrye.common.constraint.NotNull;

public class UpdateDepartment extends CreateDepartment{
    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}