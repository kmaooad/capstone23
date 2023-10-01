package edu.kmaooad.capstone23.departments.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.File;

public class AddLogo {

    @NotBlank
    @Size(min = 2, max = 50)
    private String departmentId;

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    @NotBlank
    private File logo;

    public void setLogo(File logo) {
        this.logo = logo;
    }

    public File getLogo() {
        return logo;
    }

    @NotBlank
    private String logoName;

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public String getLogoName() {
        return logoName;
    }



}
