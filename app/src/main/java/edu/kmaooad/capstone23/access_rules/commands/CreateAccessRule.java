package edu.kmaooad.capstone23.access_rules.commands;

import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateAccessRule {
    @NotNull
    @Pattern(regexp = "Allow|Deny")
    private String ruleType;

    @NotNull
    private String fromEntityId;
    @NotNull
    @Pattern(regexp = "Organisation|Department|Member")
    private String fromEntityType;

    @NotNull
    private String toEntityId;
    @NotNull
    @Pattern(regexp = "Organisation|Department|Course|Group")
    private String toEntityType;


    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public void setFromEntityId(String fromEntityId) {
        this.fromEntityId = fromEntityId;
    }

    public void setFromEntityType(String fromEntityType) {
        this.fromEntityType = fromEntityType;
    }

    public void setToEntityId(String toEntityId) {
        this.toEntityId = toEntityId;
    }

    public void setToEntityType(String toEntityType) {
        this.toEntityType = toEntityType;
    }

    public String getFromEntityId() {
        return fromEntityId;
    }

    public String getToEntityId() {
        return toEntityId;
    }

    public AccessRuleType getRuleType() {
        return AccessRuleType.valueOf(ruleType);
    }

    public AccessRuleFromEntityType getFromEntityType() {
        return AccessRuleFromEntityType.valueOf(fromEntityType);
    }

    public AccessRuleToEntityType getToEntityType() {
        return AccessRuleToEntityType.valueOf(toEntityType);
    }
}
