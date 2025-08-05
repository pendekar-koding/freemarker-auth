package com.pendekar.koding.freemarkerauth.repository;

import com.pendekar.koding.freemarkerauth.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

    Optional<UserProfile> findByDeletedIsFalseAndUsername(String username);
    Optional<UserProfile> findByDeletedIsFalseAndId(Long id);

    List<UserProfile> findAllByDeletedIsFalse();

    @Query(value = "select u from UserProfile u where u.deleted is false and (u.username like %:search% " +
            "or u.fullName like %:search% or u.email like %:search% )")
    Page<UserProfile> getPageable(@Param("search")String search, Pageable pageable);
}
