package com.pendekar.koding.freemarkerauth.repository;

import com.pendekar.koding.freemarkerauth.entity.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends CrudRepository<Books, Long> {
    Optional<Books> findByDeletedIsFalseAndId(Long id);
}
