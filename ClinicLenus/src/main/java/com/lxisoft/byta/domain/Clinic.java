package com.lxisoft.byta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Clinic.
 */
@Entity
@Table(name = "clinic")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Clinic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private ClinicDetails clinicDetails;

    @OneToOne
    @JoinColumn(unique = true)
    private Receptionist receptionist;

    @OneToOne
    @JoinColumn(unique = true)
    private Doctor doctor;

    @OneToMany(mappedBy = "clinic")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClinicPatientData> clinicPatientData = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClinicDetails getClinicDetails() {
        return clinicDetails;
    }

    public Clinic clinicDetails(ClinicDetails clinicDetails) {
        this.clinicDetails = clinicDetails;
        return this;
    }

    public void setClinicDetails(ClinicDetails clinicDetails) {
        this.clinicDetails = clinicDetails;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public Clinic receptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
        return this;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Clinic doctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Set<ClinicPatientData> getClinicPatientData() {
        return clinicPatientData;
    }

    public Clinic clinicPatientData(Set<ClinicPatientData> clinicPatientData) {
        this.clinicPatientData = clinicPatientData;
        return this;
    }

    public Clinic addClinicPatientData(ClinicPatientData clinicPatientData) {
        this.clinicPatientData.add(clinicPatientData);
        clinicPatientData.setClinic(this);
        return this;
    }

    public Clinic removeClinicPatientData(ClinicPatientData clinicPatientData) {
        this.clinicPatientData.remove(clinicPatientData);
        clinicPatientData.setClinic(null);
        return this;
    }

    public void setClinicPatientData(Set<ClinicPatientData> clinicPatientData) {
        this.clinicPatientData = clinicPatientData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Clinic clinic = (Clinic) o;
        if (clinic.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clinic.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Clinic{" +
            "id=" + getId() +
            "}";
    }
}
