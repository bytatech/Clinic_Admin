package com.lxisoft.byta.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MedicineSystem.
 */
@Entity
@Table(name = "medicine_system")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MedicineSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MedicineSystem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MedicineSystem medicineSystem = (MedicineSystem) o;
        if (medicineSystem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicineSystem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicineSystem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
