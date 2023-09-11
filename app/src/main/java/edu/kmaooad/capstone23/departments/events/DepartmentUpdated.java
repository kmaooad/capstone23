package edu.kmaooad.capstone23.departments.events;

public class DepartmentUpdated {

    private String id;
    private String name;
    private String description;
    private String parent;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getParent() {
        return parent;
    }

    public DepartmentUpdated(String id, String name, String description, String parent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

}
