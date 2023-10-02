package edu.kmaooad.capstone23.departments.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
    private String logo;

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
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
