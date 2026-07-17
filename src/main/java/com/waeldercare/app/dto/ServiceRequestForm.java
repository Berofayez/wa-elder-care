package com.waeldercare.app.dto;

import com.waeldercare.app.model.CareType;
import com.waeldercare.app.model.Relationship;
import com.waeldercare.app.model.Urgency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ServiceRequestForm {

    @NotBlank(message = "Please enter your name")
    private String requestorName;

    @NotBlank(message = "Please enter a phone number so we can call you back")
    private String requestorPhone;

    private String requestorEmail;

    @NotNull(message = "Please tell us who this request is for")
    private Relationship relationship;

    @NotBlank(message = "Please enter the name of the person who needs care")
    private String elderName;

    @NotBlank(message = "Please enter a city or zip code so we can confirm you're in our service area")
    private String elderLocation;

    @NotEmpty(message = "Please select at least one type of care needed")
    private Set<CareType> careTypes = new HashSet<>();

    @NotNull(message = "Please let us know how urgent this is")
    private Urgency urgency;

    private String howHeardAboutUs;

    private String notes;

    /** Honeypot field: real visitors never fill this in; bots often do. */
    private String website;

    public String getRequestorName() {
        return requestorName;
    }

    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

    public String getRequestorPhone() {
        return requestorPhone;
    }

    public void setRequestorPhone(String requestorPhone) {
        this.requestorPhone = requestorPhone;
    }

    public String getRequestorEmail() {
        return requestorEmail;
    }

    public void setRequestorEmail(String requestorEmail) {
        this.requestorEmail = requestorEmail;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getElderLocation() {
        return elderLocation;
    }

    public void setElderLocation(String elderLocation) {
        this.elderLocation = elderLocation;
    }

    public Set<CareType> getCareTypes() {
        return careTypes;
    }

    public void setCareTypes(Set<CareType> careTypes) {
        this.careTypes = careTypes;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public String getHowHeardAboutUs() {
        return howHeardAboutUs;
    }

    public void setHowHeardAboutUs(String howHeardAboutUs) {
        this.howHeardAboutUs = howHeardAboutUs;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isSpam() {
        return website != null && !website.isBlank();
    }
}
