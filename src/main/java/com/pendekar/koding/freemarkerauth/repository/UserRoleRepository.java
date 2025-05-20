package com.pendekar.koding.freemarkerauth.repository;

import com.pendekar.koding.freemarkerauth.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    Optional<UserRole> findByCode(String code);
}
