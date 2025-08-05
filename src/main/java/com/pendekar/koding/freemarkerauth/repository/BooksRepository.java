package com.pendekar.koding.freemarkerauth.repository;

import com.pendekar.koding.freemarkerauth.entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends CrudRepository<Books, Long> {
    Optional<Books> findByDeletedIsFalseAndId(Long id);

    List<Books> findAllByDeletedIsFalse();

    @Query(value = "select b from Books b where b.deleted is false and (b.title like %:search% or b.author like %:search% or b.publisher like %:search% )")
    Page<Books> getPageable(@Param("search")String search, Pageable pageable);
}
