package com.pendekar.koding.freemarkerauth.service.impl;

import com.pendekar.koding.freemarkerauth.entity.UserRole;
import com.pendekar.koding.freemarkerauth.repository.UserRoleRepository;
import com.pendekar.koding.freemarkerauth.service.UserRoleService;
import com.pendekar.koding.freemarkerauth.wrapper.UserRoleWrapper;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Long getNum() {
        return userRoleRepository.count();
    }

    @Override
    public void save(UserRoleWrapper wrapper) {
        UserRole role = new UserRole();
        role.setCode(wrapper.getCode());
        role.setRoleName(wrapper.getRoleName());
        userRoleRepository.save(role);
    }
}
