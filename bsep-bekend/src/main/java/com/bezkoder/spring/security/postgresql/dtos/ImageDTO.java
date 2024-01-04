package com.bezkoder.spring.security.postgresql.dtos;


import com.bezkoder.spring.security.postgresql.models.TopImage;

public class ImageDTO {
    private GuitarDTO guitar;
    private Long id;
    private String urlPath;


    public ImageDTO(){

    }

    public ImageDTO(TopImage topImage){
        this.id = topImage.getId();
        this.urlPath = topImage.getUrlPath();
        this.guitar = new GuitarDTO(topImage.getGuitar());
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

    public GuitarDTO getGuitar() {
        return guitar;
    }

    public void setGuitar(GuitarDTO guitar) {
        this.guitar = guitar;
    }
}
