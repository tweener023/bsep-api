package com.bezkoder.spring.security.postgresql.models;


import javax.persistence.*;

@Entity
@Table(name = "all_image_table")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String urlPath;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Guitar guitar;

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
