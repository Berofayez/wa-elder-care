package com.waeldercare.app.model;

public enum Urgency {
    ASAP("As soon as possible"),
    THIS_WEEK("Sometime this week"),
    PLANNING_AHEAD("Just planning ahead");

    private final String label;

    Urgency(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
