package com.lxisoft.byta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * A ClinicPatientData.
 */
@Entity
@Table(name = "clinic_patient_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClinicPatientData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_date")
    private LocalDate date;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone_no")
    private Long phoneNo;

    @ManyToOne
    private Clinic clinic;

    @OneToOne
    @JoinColumn(unique = true)
    private ClinicPatientPrivateData clinicPrivateData;

    @OneToMany(mappedBy = "clinicPatientData")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Prescription> prescriptions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public ClinicPatientData date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public ClinicPatientData name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public ClinicPatientData gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public ClinicPatientData age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public ClinicPatientData phoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public ClinicPatientData clinic(Clinic clinic) {
        this.clinic = clinic;
        return this;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public ClinicPatientPrivateData getClinicPrivateData() {
        return clinicPrivateData;
    }

    public ClinicPatientData clinicPrivateData(ClinicPatientPrivateData clinicPatientPrivateData) {
        this.clinicPrivateData = clinicPatientPrivateData;
        return this;
    }

    public void setClinicPrivateData(ClinicPatientPrivateData clinicPatientPrivateData) {
        this.clinicPrivateData = clinicPatientPrivateData;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public ClinicPatientData prescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
        return this;
    }

    public ClinicPatientData addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
        prescription.setClinicPatientData(this);
        return this;
    }

    public ClinicPatientData removePrescription(Prescription prescription) {
        this.prescriptions.remove(prescription);
        prescription.setClinicPatientData(null);
        return this;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClinicPatientData clinicPatientData = (ClinicPatientData) o;
        if (clinicPatientData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clinicPatientData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClinicPatientData{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", name='" + getName() + "'" +
            ", gender='" + getGender() + "'" +
            ", age='" + getAge() + "'" +
            ", phoneNo='" + getPhoneNo() + "'" +
            "}";
    }
}
