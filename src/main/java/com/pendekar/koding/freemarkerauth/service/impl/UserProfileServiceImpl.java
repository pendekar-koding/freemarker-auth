package com.pendekar.koding.freemarkerauth.service.impl;

import com.pendekar.koding.freemarkerauth.common.exception.StudyException;
import com.pendekar.koding.freemarkerauth.common.message.DataTableObject;
import com.pendekar.koding.freemarkerauth.common.utils.CommonUtils;
import com.pendekar.koding.freemarkerauth.entity.UserProfile;
import com.pendekar.koding.freemarkerauth.entity.UserRole;
import com.pendekar.koding.freemarkerauth.repository.UserProfileRepository;
import com.pendekar.koding.freemarkerauth.repository.UserRoleRepository;
import com.pendekar.koding.freemarkerauth.service.UserProfileService;
import com.pendekar.koding.freemarkerauth.wrapper.UserProfileWrapper;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long getNum() {
        return userProfileRepository.count();
    }

    @Override
    public UserProfileWrapper save(UserProfileWrapper wrapper) throws StudyException {
        if (wrapper.getUpdatedBy() != null) {
            wrapper.setUpdatedBy(wrapper.getUpdatedBy());
        } else {
        wrapper.setUpdatedBy(CommonUtils.getUsername());
        }
        wrapper.setUpdatedDate(new Date());
        wrapper.setVersion(1);
        return toWrapper(userProfileRepository.save(toEntity(wrapper)));
    }

    @Override
    public UserProfileWrapper getById(Long pk) throws StudyException {
        Optional<UserProfile> optional = userProfileRepository.findByDeletedIsFalseAndId(pk);
        return optional.map(this::toWrapper).orElse(null);
    }

    @Override
    public Boolean delete(Long pk) throws StudyException {
        Optional<UserProfile> optional = userProfileRepository.findByDeletedIsFalseAndId(pk);
        if (optional.isPresent()) {
            UserProfile userProfile = optional.get();
            userProfile.setDeleted(true);
            userProfile.setVersion(1);
            userProfile.setModifiedDate(new Date());
            userProfileRepository.save(userProfile);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserProfileWrapper> getAll() throws StudyException {
        return toWrapperList((List<UserProfile>) userProfileRepository.findAllByDeletedIsFalse());
    }

    @Override
    public void deleteAll() throws StudyException {

    }

    @Override
    public Page<UserProfileWrapper> getPageableList(String sSearch, int startPage, int pageSize, Sort sort) throws StudyException {
        int page = DataTableObject.getPageFromStartAndLength(startPage, pageSize);
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        if (userProfileRepository.count() == 0) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        } else {
            Page<UserProfile> userProfilePage = userProfileRepository.getPageable(sSearch, pageable);
            List<UserProfileWrapper> wrappers = toWrapperList(userProfilePage.getContent());
            return new PageImpl<>(wrappers, pageable, userProfilePage.getTotalElements());
        }
    }

    @Override
    public UserProfileWrapper findByUsername(String username) throws StudyException {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByDeletedIsFalseAndUsername(username);
        return userProfileOptional.map(this::toWrapper).orElse(null);
    }

    @Override
    public Boolean inActive(String username) throws StudyException {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByDeletedIsFalseAndUsername(username);
        if (userProfileOptional.isPresent()) {
            UserProfile model = userProfileOptional.get();
            model.setActive(false);
            model.setVersion(1);
            model.setModifiedDate(new Date());
            userProfileRepository.save(model);
        }
        return true;
    }

    private UserProfileWrapper toWrapper(UserProfile entity) {
        UserProfileWrapper wrapper = new UserProfileWrapper();
        wrapper.setId(entity.getId());
        wrapper.setUsername(entity.getUsername());
//        wrapper.setPassword(entity.getPassword());
        wrapper.setActive(entity.getActive());
        wrapper.setFullName(entity.getFullName());
        wrapper.setEmail(entity.getEmail());

        if (entity.getRole() != null) {
            UserRole role = entity.getRole();
            wrapper.setCodeRole(role.getCode());
            wrapper.setNameRole(role.getRoleName());
        }
        return wrapper;
    }

    private List<UserProfileWrapper> toWrapperList(List<UserProfile> modelList) {
        List<UserProfileWrapper> wrappers = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (UserProfile profile : modelList) {
                wrappers.add(toWrapper(profile));
            }
        }
        return wrappers;
    }

    private UserProfile toEntity(UserProfileWrapper wrapper) {
        UserProfile entity = new UserProfile();
        if (wrapper.getId() != null) {
            Optional<UserProfile> optional = userProfileRepository.findById(wrapper.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }
        entity.setDeleted(wrapper.getDeleted());
        entity.setDescription(wrapper.getDescription());
        entity.setVersion(wrapper.getVersion());
        if (wrapper.getCreatedBy() != null && wrapper.getCreatedDate() != null) {
            entity.setCreatedBy(wrapper.getCreatedBy());
            entity.setCreatedDate(wrapper.getCreatedDate());
        }
        if (wrapper.getUpdatedBy() != null && wrapper.getUpdatedDate() != null) {
            entity.setModifiedBy(wrapper.getUpdatedBy());
            entity.setModifiedDate(wrapper.getUpdatedDate());
        }

        entity.setUsername(wrapper.getUsername());
        entity.setPassword(passwordEncoder.encode(wrapper.getPassword()));
        entity.setActive(wrapper.getActive());
        entity.setFullName(wrapper.getFullName());
        entity.setEmail(wrapper.getEmail());

        if (wrapper.getCodeRole() != null) {
            Optional<UserRole> optional = userRoleRepository.findByCode(wrapper.getCodeRole().toUpperCase());
            if (optional.isPresent()) {
                entity.setRole(optional.get());
            }
        }

        return entity;
    }
}
