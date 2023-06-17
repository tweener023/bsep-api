package com.bezkoder.spring.security.postgresql.models;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Boolean canCreate;
    Boolean canRead;
    Boolean canUpdate;
    Boolean canDelete;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Permissions(){

    }

    public Permissions(Boolean create, Boolean read, Boolean update, Boolean delete, User user) {
        this.canCreate = create;
        this.canRead = read;
        this.canUpdate = update;
        this.canDelete = delete;
        this.user = user;
    }

    public Boolean getCreate() {
        return canCreate;
    }

    public void setCreate(Boolean create) {
        this.canCreate = create;
    }

    public Boolean getRead() {
        return canRead;
    }

    public void setRead(Boolean read) {
        this.canRead = read;
    }

    public Boolean getUpdate() {
        return canUpdate;
    }

    public void setUpdate(Boolean update) {
        this.canUpdate = update;
    }

    public Boolean getDelete() {
        return canDelete;
    }

    public void setDelete(Boolean delete) {
        this.canDelete = delete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
