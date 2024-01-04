package com.bezkoder.spring.security.postgresql.models;

import com.bezkoder.spring.security.postgresql.models.enums.StateOfGuitar;
import com.bezkoder.spring.security.postgresql.models.enums.TypeOfGuitar;
import com.bezkoder.spring.security.postgresql.models.enums.TypeOfMagnets;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guitars")
public class Guitar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String manufacturerOfGuitar;

    @Column
    private String modelOfGuitar;

    @Column
    private Integer yearOfProduction;

    @Column
    private Integer price;

    @Column
    @Enumerated(EnumType.STRING)
    private StateOfGuitar stateOfGuitar;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOfGuitar typeOfGuitar;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOfMagnets typeOfMagnets;

    @Column
    private String tuners;

    @Column
    private String typeOfWood;

    @Column
    private String description;

    @OneToMany(mappedBy = "guitar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Image> images = new HashSet<Image>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturerOfGuitar() {
        return manufacturerOfGuitar;
    }

    public void setManufacturerOfGuitar(String manufacturerOfGuitar) {
        this.manufacturerOfGuitar = manufacturerOfGuitar;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public StateOfGuitar getStateOfGuitar() {
        return stateOfGuitar;
    }

    public void setStateOfGuitar(StateOfGuitar stateOfGuitar) {
        this.stateOfGuitar = stateOfGuitar;
    }

    public TypeOfGuitar getTypeOfGuitar() {
        return typeOfGuitar;
    }

    public void setTypeOfGuitar(TypeOfGuitar typeOfGuitar) {
        this.typeOfGuitar = typeOfGuitar;
    }

    public TypeOfMagnets getTypeOfMagnets() {
        return typeOfMagnets;
    }

    public void setTypeOfMagnets(TypeOfMagnets typeOfMagnets) {
        this.typeOfMagnets = typeOfMagnets;
    }

    public String getTuners() {
        return tuners;
    }

    public void setTuners(String tuners) {
        this.tuners = tuners;
    }

    public String getTypeOfWood() {
        return typeOfWood;
    }

    public void setTypeOfWood(String typeOfWood) {
        this.typeOfWood = typeOfWood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModelOfGuitar() {
        return modelOfGuitar;
    }

    public void setModelOfGuitar(String modelOfGuitar) {
        this.modelOfGuitar = modelOfGuitar;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
