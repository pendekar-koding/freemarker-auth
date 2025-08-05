package com.pendekar.koding.freemarkerauth.service;

import com.pendekar.koding.freemarkerauth.common.exception.StudyException;
import com.pendekar.koding.freemarkerauth.common.service.CommonService;
import com.pendekar.koding.freemarkerauth.wrapper.UserProfileWrapper;

public interface UserProfileService extends CommonService<UserProfileWrapper, Long> {

    Long getNum();
//    UserProfileWrapper save(UserProfileWrapper wrapper) throws StudyException;
    UserProfileWrapper findByUsername(String username) throws StudyException;
    Boolean inActive(String username) throws StudyException;
}
