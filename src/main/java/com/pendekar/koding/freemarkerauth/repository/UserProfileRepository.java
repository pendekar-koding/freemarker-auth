package com.pendekar.koding.freemarkerauth.repository;

import com.pendekar.koding.freemarkerauth.entity.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

    Optional<UserProfile> findByDeletedIsFalseAndUsername(String username);
}
