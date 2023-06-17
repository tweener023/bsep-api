package com.bezkoder.spring.security.postgresql.dtos;

import com.bezkoder.spring.security.postgresql.models.Permissions;
import com.bezkoder.spring.security.postgresql.models.Skill;
import com.bezkoder.spring.security.postgresql.models.User;

public class PermissionsDTO {
    private Long id;
    Boolean canCreate;
    Boolean canRead;
    Boolean canUpdate;
    Boolean canDelete;


    public PermissionsDTO() {
    }

    public PermissionsDTO(Permissions permissions){
        id = permissions.getId();
        canCreate = permissions.getCreate();
        canUpdate = permissions.getUpdate();
        canRead = permissions.getRead();
        canDelete = permissions.getDelete();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCanCreate() {
        return canCreate;
    }

    public void setCanCreate(Boolean canCreate) {
        this.canCreate = canCreate;
    }

    public Boolean getCanRead() {
        return canRead;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }
}
