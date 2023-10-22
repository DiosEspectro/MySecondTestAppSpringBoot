package ru.diosespectro.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Positions {
    DEV ("Developer", 2.2, false),
    HR ("HR-specialist", 1.2, false),
    TL ("Manager", 2.6, true),
    QA ("QA-engineer", 2.1, false),
    CEO ("Chief Executive Officer", 10.0, false),
    SYSADMIN ("System Administrator", 1.5, false);

    private final String name;
    private final double positionCoefficient;
    private final boolean isManager;

    Positions(String name, double positionCoefficient, boolean isManager) {
        this.name = name;
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
