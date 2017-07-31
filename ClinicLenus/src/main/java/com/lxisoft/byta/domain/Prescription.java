package com.lxisoft.byta.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Prescription.
 */
@Entity
@Table(name = "prescription")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_date")
    private LocalDate date;

    @NotNull
    @Column(name = "doctor_name", nullable = false)
    private String doctorName;

    @ManyToOne
    private ClinicPatientData clinicPatientData;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "prescription_drugs",
               joinColumns = @JoinColumn(name="prescriptions_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="drugs_id", referencedColumnName="id"))
    private Set<Drugs> drugs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Prescription date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public Prescription doctorName(String doctorName) {
        this.doctorName = doctorName;
        return this;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public ClinicPatientData getClinicPatientData() {
        return clinicPatientData;
    }

    public Prescription clinicPatientData(ClinicPatientData clinicPatientData) {
        this.clinicPatientData = clinicPatientData;
        return this;
    }

    public void setClinicPatientData(ClinicPatientData clinicPatientData) {
        this.clinicPatientData = clinicPatientData;
    }

    public Set<Drugs> getDrugs() {
        return drugs;
    }

    public Prescription drugs(Set<Drugs> drugs) {
        this.drugs = drugs;
        return this;
    }

    public Prescription addDrugs(Drugs drugs) {
        this.drugs.add(drugs);
        drugs.getDrugs().add(this);
        return this;
    }

    public Prescription removeDrugs(Drugs drugs) {
        this.drugs.remove(drugs);
        drugs.getDrugs().remove(this);
        return this;
    }

    public void setDrugs(Set<Drugs> drugs) {
        this.drugs = drugs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Prescription prescription = (Prescription) o;
        if (prescription.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prescription.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Prescription{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", doctorName='" + getDoctorName() + "'" +
            "}";
    }
}
