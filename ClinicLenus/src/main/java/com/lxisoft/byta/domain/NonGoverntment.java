package com.lxisoft.byta.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A NonGoverntment.
 */
@Entity
@Table(name = "non_governtment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NonGoverntment implements Serializable {

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

    public NonGoverntment name(String name) {
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
        NonGoverntment nonGoverntment = (NonGoverntment) o;
        if (nonGoverntment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nonGoverntment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NonGoverntment{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
