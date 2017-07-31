package com.lxisoft.byta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Drugs.
 */
@Entity
@Table(name = "drugs")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Drugs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "drug_name", nullable = false)
    private String drugName;

    @Column(name = "drug_instruction")
    private String drugInstruction;

    @Column(name = "frequency")
    private Integer frequency;

    @Column(name = "total_packing")
    private Integer totalPacking;

    @ManyToMany(mappedBy = "drugs")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Prescription> drugs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public Drugs drugName(String drugName) {
        this.drugName = drugName;
        return this;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugInstruction() {
        return drugInstruction;
    }

    public Drugs drugInstruction(String drugInstruction) {
        this.drugInstruction = drugInstruction;
        return this;
    }

    public void setDrugInstruction(String drugInstruction) {
        this.drugInstruction = drugInstruction;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public Drugs frequency(Integer frequency) {
        this.frequency = frequency;
        return this;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getTotalPacking() {
        return totalPacking;
    }

    public Drugs totalPacking(Integer totalPacking) {
        this.totalPacking = totalPacking;
        return this;
    }

    public void setTotalPacking(Integer totalPacking) {
        this.totalPacking = totalPacking;
    }

    public Set<Prescription> getDrugs() {
        return drugs;
    }

    public Drugs drugs(Set<Prescription> prescriptions) {
        this.drugs = prescriptions;
        return this;
    }

    public Drugs addDrug(Prescription prescription) {
        this.drugs.add(prescription);
        prescription.getDrugs().add(this);
        return this;
    }

    public Drugs removeDrug(Prescription prescription) {
        this.drugs.remove(prescription);
        prescription.getDrugs().remove(this);
        return this;
    }

    public void setDrugs(Set<Prescription> prescriptions) {
        this.drugs = prescriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Drugs drugs = (Drugs) o;
        if (drugs.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), drugs.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Drugs{" +
            "id=" + getId() +
            ", drugName='" + getDrugName() + "'" +
            ", drugInstruction='" + getDrugInstruction() + "'" +
            ", frequency='" + getFrequency() + "'" +
            ", totalPacking='" + getTotalPacking() + "'" +
            "}";
    }
}
