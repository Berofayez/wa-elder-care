package com.waeldercare.app.model;

public enum CareType {
    PERSONAL_CARE("Personal care (bathing, hygiene, changing)"),
    MEALS("Meal preparation"),
    MOBILITY("Mobility assistance (walking, transfers)"),
    COMPANIONSHIP("Companionship");

    private final String label;

    CareType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
