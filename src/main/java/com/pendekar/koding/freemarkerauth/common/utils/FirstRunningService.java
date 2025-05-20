package com.pendekar.koding.freemarkerauth.common.utils;

import com.pendekar.koding.freemarkerauth.service.UserProfileService;
import com.pendekar.koding.freemarkerauth.service.UserRoleService;
import com.pendekar.koding.freemarkerauth.wrapper.UserProfileWrapper;
import com.pendekar.koding.freemarkerauth.wrapper.UserRoleWrapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FirstRunningService implements CommandLineRunner {

    private final UserProfileService userProfileService;
    private final UserRoleService userRoleService;

    public FirstRunningService(UserProfileService userProfileService, UserRoleService userRoleService) {
        this.userProfileService = userProfileService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRoleService.getNum() == 0) {
            UserRoleWrapper userRoleWrapper = new UserRoleWrapper();
            userRoleWrapper.setRoleName("Super Administrator");
            userRoleWrapper.setCode("SADM");
            userRoleService.save(userRoleWrapper);
        }

        if (userProfileService.getNum() == 0) {
            UserProfileWrapper wrapper = getUserProfileWrapper();
            userProfileService.save(wrapper);
        }
    }

    private static UserProfileWrapper getUserProfileWrapper() {
        UserProfileWrapper wrapper = new UserProfileWrapper();
        wrapper.setDeleted(false);
        wrapper.setVersion(1);
        wrapper.setCreatedBy("System");
        wrapper.setCreatedDate(new Date());
        wrapper.setUpdatedBy("System");
        wrapper.setUpdatedDate(new Date());

        wrapper.setFullName("Administrator");
        wrapper.setUsername("admin");
        wrapper.setPassword("admin@123");
        wrapper.setEmail("admin@admin.com");
        wrapper.setActive(true);
        wrapper.setCodeRole("SADM");
        return wrapper;
    }
}
