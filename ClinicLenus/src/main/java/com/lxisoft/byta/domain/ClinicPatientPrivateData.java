package com.lxisoft.byta.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ClinicPatientPrivateData.
 */
@Entity
@Table(name = "clinic_patient_private_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClinicPatientPrivateData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private Long zip;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "social_media_id")
    private String socialMediaId;

    @Column(name = "profession")
    private String profession;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public ClinicPatientPrivateData patientId(Integer patientId) {
        this.patientId = patientId;
        return this;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getAddress() {
        return address;
    }

    public ClinicPatientPrivateData address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public ClinicPatientPrivateData city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getZip() {
        return zip;
    }

    public ClinicPatientPrivateData zip(Long zip) {
        this.zip = zip;
        return this;
    }

    public void setZip(Long zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public ClinicPatientPrivateData email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public ClinicPatientPrivateData country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSocialMediaId() {
        return socialMediaId;
    }

    public ClinicPatientPrivateData socialMediaId(String socialMediaId) {
        this.socialMediaId = socialMediaId;
        return this;
    }

    public void setSocialMediaId(String socialMediaId) {
        this.socialMediaId = socialMediaId;
    }

    public String getProfession() {
        return profession;
    }

    public ClinicPatientPrivateData profession(String profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClinicPatientPrivateData clinicPatientPrivateData = (ClinicPatientPrivateData) o;
        if (clinicPatientPrivateData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clinicPatientPrivateData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClinicPatientPrivateData{" +
            "id=" + getId() +
            ", patientId='" + getPatientId() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", zip='" + getZip() + "'" +
            ", email='" + getEmail() + "'" +
            ", country='" + getCountry() + "'" +
            ", socialMediaId='" + getSocialMediaId() + "'" +
            ", profession='" + getProfession() + "'" +
            "}";
    }
}
