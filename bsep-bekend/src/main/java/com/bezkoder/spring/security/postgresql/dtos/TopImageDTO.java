package com.bezkoder.spring.security.postgresql.dtos;

import com.bezkoder.spring.security.postgresql.models.Guitar;
import com.bezkoder.spring.security.postgresql.models.TopImage;

public class TopImageDTO {

    private Long id;
    private String urlPath;
    private Guitar guitar;

    private TopImageDTO(){

    }

    public TopImageDTO(TopImage topImage){
        this.id = topImage.getId();
        this.urlPath = topImage.getUrlPath();
        this.guitar = topImage.getGuitar();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public Guitar getGuitar() {
        return guitar;
    }

    public void setGuitar(Guitar guitar) {
        this.guitar = guitar;
    }
}
