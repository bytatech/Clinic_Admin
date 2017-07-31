package com.lxisoft.byta.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A ClinicDetails.
 */
@Entity
@Table(name = "clinic_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClinicDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "pincode")
    private Long pincode;

    @Column(name = "country")
    private String country;

    @Column(name = "mobile")
    private Long mobile;

    @Column(name = "clinic_timing")
    private LocalDate clinicTiming;

    @Column(name = "category")
    private String category;

    @Column(name = "landmarks")
    private String landmarks;

    @Column(name = "website")
    private String website;

    @Column(name = "clinic_description")
    private String clinicDescription;

    @Lob
    @Column(name = "clinic_images")
    private byte[] clinicImages;

    @Column(name = "clinic_images_content_type")
    private String clinicImagesContentType;

    @Column(name = "location")
    private String location;

    @OneToOne
    @JoinColumn(unique = true)
    private ClinicOwnerInfo clinicOwnerInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private Governtment governtment;

    @OneToOne
    @JoinColumn(unique = true)
    private NonGoverntment nonGoverntment;

    @OneToOne
    @JoinColumn(unique = true)
    private MedicineSystem medicineSystem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ClinicDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public ClinicDetails address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPincode() {
        return pincode;
    }

    public ClinicDetails pincode(Long pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public ClinicDetails country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getMobile() {
        return mobile;
    }

    public ClinicDetails mobile(Long mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public LocalDate getClinicTiming() {
        return clinicTiming;
    }

    public ClinicDetails clinicTiming(LocalDate clinicTiming) {
        this.clinicTiming = clinicTiming;
        return this;
    }

    public void setClinicTiming(LocalDate clinicTiming) {
        this.clinicTiming = clinicTiming;
    }

    public String getCategory() {
        return category;
    }

    public ClinicDetails category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLandmarks() {
        return landmarks;
    }

    public ClinicDetails landmarks(String landmarks) {
        this.landmarks = landmarks;
        return this;
    }

    public void setLandmarks(String landmarks) {
        this.landmarks = landmarks;
    }

    public String getWebsite() {
        return website;
    }

    public ClinicDetails website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getClinicDescription() {
        return clinicDescription;
    }

    public ClinicDetails clinicDescription(String clinicDescription) {
        this.clinicDescription = clinicDescription;
        return this;
    }

    public void setClinicDescription(String clinicDescription) {
        this.clinicDescription = clinicDescription;
    }

    public byte[] getClinicImages() {
        return clinicImages;
    }

    public ClinicDetails clinicImages(byte[] clinicImages) {
        this.clinicImages = clinicImages;
        return this;
    }

    public void setClinicImages(byte[] clinicImages) {
        this.clinicImages = clinicImages;
    }

    public String getClinicImagesContentType() {
        return clinicImagesContentType;
    }

    public ClinicDetails clinicImagesContentType(String clinicImagesContentType) {
        this.clinicImagesContentType = clinicImagesContentType;
        return this;
    }

    public void setClinicImagesContentType(String clinicImagesContentType) {
        this.clinicImagesContentType = clinicImagesContentType;
    }

    public String getLocation() {
        return location;
    }

    public ClinicDetails location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ClinicOwnerInfo getClinicOwnerInfo() {
        return clinicOwnerInfo;
    }

    public ClinicDetails clinicOwnerInfo(ClinicOwnerInfo clinicOwnerInfo) {
        this.clinicOwnerInfo = clinicOwnerInfo;
        return this;
    }

    public void setClinicOwnerInfo(ClinicOwnerInfo clinicOwnerInfo) {
        this.clinicOwnerInfo = clinicOwnerInfo;
    }

    public Governtment getGoverntment() {
        return governtment;
    }

    public ClinicDetails governtment(Governtment governtment) {
        this.governtment = governtment;
        return this;
    }

    public void setGoverntment(Governtment governtment) {
        this.governtment = governtment;
    }

    public NonGoverntment getNonGoverntment() {
        return nonGoverntment;
    }

    public ClinicDetails nonGoverntment(NonGoverntment nonGoverntment) {
        this.nonGoverntment = nonGoverntment;
        return this;
    }

    public void setNonGoverntment(NonGoverntment nonGoverntment) {
        this.nonGoverntment = nonGoverntment;
    }

    public MedicineSystem getMedicineSystem() {
        return medicineSystem;
    }

    public ClinicDetails medicineSystem(MedicineSystem medicineSystem) {
        this.medicineSystem = medicineSystem;
        return this;
    }

    public void setMedicineSystem(MedicineSystem medicineSystem) {
        this.medicineSystem = medicineSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClinicDetails clinicDetails = (ClinicDetails) o;
        if (clinicDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clinicDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClinicDetails{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", pincode='" + getPincode() + "'" +
            ", country='" + getCountry() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", clinicTiming='" + getClinicTiming() + "'" +
            ", category='" + getCategory() + "'" +
            ", landmarks='" + getLandmarks() + "'" +
            ", website='" + getWebsite() + "'" +
            ", clinicDescription='" + getClinicDescription() + "'" +
            ", clinicImages='" + getClinicImages() + "'" +
            ", clinicImagesContentType='" + clinicImagesContentType + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }
}
