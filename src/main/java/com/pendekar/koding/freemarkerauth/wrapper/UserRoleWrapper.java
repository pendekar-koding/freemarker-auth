package com.pendekar.koding.freemarkerauth.wrapper;

import com.pendekar.koding.freemarkerauth.common.wrapper.EntityBaseWrapper;

public class UserRoleWrapper extends EntityBaseWrapper {

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
