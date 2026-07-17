package com.waeldercare.app.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private String requestorName;

    @Column(nullable = false)
    private String requestorPhone;

    private String requestorEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Relationship relationship;

    @Column(nullable = false)
    private String elderName;

    @Column(nullable = false)
    private String elderLocation;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "service_request_care_types", joinColumns = @JoinColumn(name = "service_request_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "care_type")
    private Set<CareType> careTypes = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Urgency urgency;

    private String howHeardAboutUs;

    @Lob
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.NEW;

    @Column(nullable = false)
    private Instant statusUpdatedAt = Instant.now();

    @Lob
    private String internalNotes;

    @ManyToOne
    @JoinColumn(name = "handled_by_admin_id")
    private AdminUser handledBy;

    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
        this.statusUpdatedAt = Instant.now();
    }

    public Instant getStatusUpdatedAt() {
        return statusUpdatedAt;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public AdminUser getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(AdminUser handledBy) {
        this.handledBy = handledBy;
    }
}
