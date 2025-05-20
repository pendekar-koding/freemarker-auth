package com.pendekar.koding.freemarkerauth.service;

import com.pendekar.koding.freemarkerauth.wrapper.UserRoleWrapper;

public interface UserRoleService {

    Long getNum();
    void save(UserRoleWrapper wrapper);
}
