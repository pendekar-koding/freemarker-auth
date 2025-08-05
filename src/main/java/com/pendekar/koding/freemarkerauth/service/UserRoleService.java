package com.pendekar.koding.freemarkerauth.service;

import com.pendekar.koding.freemarkerauth.common.service.CommonService;
import com.pendekar.koding.freemarkerauth.wrapper.UserRoleWrapper;

public interface UserRoleService extends CommonService<UserRoleWrapper, Long> {

    Long getNum();
    void firstSave(UserRoleWrapper wrapper);
}
