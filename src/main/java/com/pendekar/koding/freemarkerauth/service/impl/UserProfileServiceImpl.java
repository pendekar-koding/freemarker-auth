package com.pendekar.koding.freemarkerauth.service.impl;

import com.pendekar.koding.freemarkerauth.common.exception.StudyException;
import com.pendekar.koding.freemarkerauth.entity.UserProfile;
import com.pendekar.koding.freemarkerauth.entity.UserRole;
import com.pendekar.koding.freemarkerauth.repository.UserProfileRepository;
import com.pendekar.koding.freemarkerauth.repository.UserRoleRepository;
import com.pendekar.koding.freemarkerauth.service.UserProfileService;
import com.pendekar.koding.freemarkerauth.wrapper.UserProfileWrapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return toWrapper(userProfileRepository.save(toEntity(wrapper)));
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
