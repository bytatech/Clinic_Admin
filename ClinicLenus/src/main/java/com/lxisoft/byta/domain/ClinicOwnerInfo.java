package com.lxisoft.byta.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ClinicOwnerInfo.
 */
@Entity
@Table(name = "clinic_owner_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClinicOwnerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "designation")
    private String designation;

    @Column(name = "mobile")
    private Long mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "registration_no")
    private Long registrationNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ClinicOwnerInfo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public ClinicOwnerInfo designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getMobile() {
        return mobile;
    }

    public ClinicOwnerInfo mobile(Long mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public ClinicOwnerInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public ClinicOwnerInfo address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getRegistrationNo() {
        return registrationNo;
    }

    public ClinicOwnerInfo registrationNo(Long registrationNo) {
        this.registrationNo = registrationNo;
        return this;
    }

    public void setRegistrationNo(Long registrationNo) {
        this.registrationNo = registrationNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClinicOwnerInfo clinicOwnerInfo = (ClinicOwnerInfo) o;
        if (clinicOwnerInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clinicOwnerInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClinicOwnerInfo{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", registrationNo='" + getRegistrationNo() + "'" +
            "}";
    }
}
