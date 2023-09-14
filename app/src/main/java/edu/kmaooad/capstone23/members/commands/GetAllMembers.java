package edu.kmaooad.capstone23.members.commands;

import jakarta.validation.constraints.Min;

public class GetAllMembers {
    @Min(1)
    private int page;
    @Min(1)
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
