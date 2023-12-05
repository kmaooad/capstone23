package edu.kmaooad.capstone23.events.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class CreateNotification {
    @NotEmpty
    private String userId;
    @NotEmpty
    @Pattern(regexp = "EMAIL|SMS|TELEGRAM")
    private String method;
    @NotEmpty // Тип події, тобто на яку команду реагуємо
    private String command;

    public String getUserId() {
        return userId;
    }

    public String getMethod() {
        return method;
    }

    public String getCommand() {
        return command;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
