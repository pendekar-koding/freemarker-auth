package com.pendekar.koding.freemarkerauth.service.impl;

import com.pendekar.koding.freemarkerauth.common.exception.StudyException;
import com.pendekar.koding.freemarkerauth.common.message.DataTableObject;
import com.pendekar.koding.freemarkerauth.entity.UserRole;
import com.pendekar.koding.freemarkerauth.repository.UserRoleRepository;
import com.pendekar.koding.freemarkerauth.service.UserRoleService;
import com.pendekar.koding.freemarkerauth.wrapper.UserRoleWrapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void firstSave(UserRoleWrapper wrapper) {
        UserRole role = new UserRole();
        role.setCode(wrapper.getCode().toUpperCase());
        role.setRoleName(wrapper.getRoleName());
        userRoleRepository.save(role);
    }

    @Override
    public UserRoleWrapper save(UserRoleWrapper entity) throws StudyException {
        return toWrapper(userRoleRepository.save(toEntity(entity)));
    }

    @Override
    public UserRoleWrapper getById(Long pk) throws StudyException {
        Optional<UserRole> optional = userRoleRepository.findById(pk);
        return optional.map(this::toWrapper).orElse(null);
    }

    @Override
    public Boolean delete(Long pk) throws StudyException {
        Optional<UserRole> optional = userRoleRepository.findById(pk);
        if (optional.isPresent()) {
            UserRole role = optional.get();
            userRoleRepository.deleteById(role.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserRoleWrapper> getAll() throws StudyException {
        return toWrapperList((List<UserRole>) userRoleRepository.findAll());
    }

    @Override
    public void deleteAll() throws StudyException {
        // Not Implement
    }

    @Override
    public Page<UserRoleWrapper> getPageableList(String sSearch, int startPage, int pageSize, Sort sort) throws StudyException {
        int page = DataTableObject.getPageFromStartAndLength(startPage, pageSize);
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        if (userRoleRepository.count() == 0) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        } else {
            Page<UserRole> rolePage = userRoleRepository.getPageable(sSearch, pageable);
            List<UserRoleWrapper> wrappers = toWrapperList(rolePage.getContent());
            return new PageImpl<>(wrappers, pageable, rolePage.getTotalElements());
        }
    }

    private UserRoleWrapper toWrapper(UserRole entity) {
        UserRoleWrapper wrapper = new UserRoleWrapper();
        wrapper.setId(entity.getId());
        wrapper.setDescription(entity.getDescription());

        wrapper.setCode(entity.getCode());
        wrapper.setRoleName(entity.getRoleName());

        return wrapper;
    }

    private UserRole toEntity(UserRoleWrapper wrapper) {
        UserRole entity = new UserRole();
        Optional<UserRole> optional = userRoleRepository.findByCode(wrapper.getCode().trim());
        if (optional.isPresent()) {
            entity = optional.get();
        }
        entity.setDescription(wrapper.getDescription());

        entity.setCode(wrapper.getCode());
        entity.setRoleName(wrapper.getRoleName());

        return entity;
    }

    private List<UserRoleWrapper> toWrapperList(List<UserRole> modelList) {
        List<UserRoleWrapper> wrappers = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (UserRole role : modelList) {
                wrappers.add(toWrapper(role));
            }
        }
        return wrappers;
    }
}
