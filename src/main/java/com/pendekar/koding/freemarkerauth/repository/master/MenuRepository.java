package com.pendekar.koding.freemarkerauth.repository.master;

import com.pendekar.koding.freemarkerauth.entity.master.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

    List<Menu> findAllByDeletedIsFalseAndParentIsTrueOrderBySequenceAsc();
}
