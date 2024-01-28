package com.bezkoder.spring.security.postgresql.dtos;

import com.bezkoder.spring.security.postgresql.models.Guitar;
import com.bezkoder.spring.security.postgresql.models.enums.StateOfGuitar;
import com.bezkoder.spring.security.postgresql.models.enums.TypeOfGuitar;
import com.bezkoder.spring.security.postgresql.models.enums.TypeOfMagnets;


public class GuitarDTO {

    private Long id;
    private String manufacturerOfGuitar;
    private String modelOfGuitar;
    private Integer yearOfProduction;
    private Integer price;
    private StateOfGuitar stateOfGuitar;
    private TypeOfGuitar typeOfGuitar;
    private TypeOfMagnets typeOfMagnets;
    private String tuners;
    private String typeOfWood;
    private String description;
    private Long userId;


    public GuitarDTO(){

    }

    public GuitarDTO(Guitar guitar){
        id = guitar.getId();
        manufacturerOfGuitar = guitar.getManufacturerOfGuitar();
        modelOfGuitar = guitar.getModelOfGuitar();
        yearOfProduction = guitar.getYearOfProduction();
        price = guitar.getPrice();
        stateOfGuitar = guitar.getStateOfGuitar();
        typeOfGuitar = guitar.getTypeOfGuitar();
        typeOfMagnets = guitar.getTypeOfMagnets();
        tuners = guitar.getTuners();
        typeOfWood = guitar.getTypeOfWood();
        description = guitar.getDescription();
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
