package com.pendekar.koding.freemarkerauth.entity;

import com.pendekar.koding.freemarkerauth.common.model.EntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole extends EntityBase {
    private String code;
    private String roleName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
