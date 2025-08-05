package com.pendekar.koding.freemarkerauth.service.impl.master;

import com.pendekar.koding.freemarkerauth.common.exception.StudyException;
import com.pendekar.koding.freemarkerauth.common.message.DataTableObject;
import com.pendekar.koding.freemarkerauth.entity.Books;
import com.pendekar.koding.freemarkerauth.repository.BooksRepository;
import com.pendekar.koding.freemarkerauth.service.master.BookService;
import com.pendekar.koding.freemarkerauth.wrapper.BooksWrapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BooksRepository booksRepository;

    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    private BooksWrapper toWrapper(Books entity) {
        BooksWrapper wrapper = new BooksWrapper();
        wrapper.setId(entity.getId());
        wrapper.setVersion(entity.getVersion());
        wrapper.setDeleted(entity.getDeleted());

        wrapper.setAuthor(entity.getAuthor());
        wrapper.setTitle(entity.getTitle());
        wrapper.setPublisher(entity.getPublisher());

        return wrapper;
    }

    private Books toEntity(BooksWrapper wrapper) {
        Books entity = new Books();
        if (wrapper.getId() != null) {
            Optional<Books> optional = booksRepository.findById(wrapper.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }

        entity.setDeleted(wrapper.getDeleted());
        entity.setVersion(wrapper.getVersion());
        entity.setDescription(wrapper.getDescription());

        if (wrapper.getCreatedBy() != null && wrapper.getCreatedDate() != null) {
            entity.setCreatedBy(wrapper.getCreatedBy());
            entity.setCreatedDate(wrapper.getCreatedDate());
        }

        if (wrapper.getUpdatedBy() != null && wrapper.getUpdatedDate() != null) {
            entity.setModifiedBy(wrapper.getUpdatedBy());
            entity.setModifiedDate(wrapper.getUpdatedDate());
        }

        entity.setTitle(wrapper.getTitle());
        entity.setAuthor(wrapper.getAuthor());
        entity.setPublisher(wrapper.getPublisher());

        return entity;
    }

    private List<BooksWrapper> toWrapperList(List<Books> modelList) {
        List<BooksWrapper> wrappers = new ArrayList<>();
        if (modelList != null && !modelList.isEmpty()) {
            for (Books model : modelList) {
                wrappers.add(toWrapper(model));
            }
        }
        return wrappers;
    }

    @Override
    public Long getNum() {
        return booksRepository.count();
    }

    @Override
    public BooksWrapper save(BooksWrapper entity) throws StudyException {
        return toWrapper(booksRepository.save(toEntity(entity)));
    }

    @Override
    public BooksWrapper getById(Long pk) throws StudyException {
        Optional<Books> optional = booksRepository.findByDeletedIsFalseAndId(pk);
        return optional.map(this::toWrapper).orElse(null);
    }

    @Override
    public Boolean delete(Long pk) throws StudyException {
        Optional<Books> optional = booksRepository.findById(pk);
        if (optional.isPresent()) {
            Books model = optional.get();
            model.setDeleted(true);
            model.setVersion(1);
            model.setModifiedDate(new Date());
            booksRepository.save(model);
        }
        return true;
    }

    @Override
    public List<BooksWrapper> getAll() throws StudyException {
        return toWrapperList((List<Books>) booksRepository.findAllByDeletedIsFalse());
    }

    @Override
    public void deleteAll() throws StudyException {
        // Not implement
    }

    @Override
    public Page<BooksWrapper> getPageableList(String sSearch, int startPage, int pageSize, Sort sort) throws StudyException {
        int page = DataTableObject.getPageFromStartAndLength(startPage, pageSize);
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        if (booksRepository.count() == 0) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        } else {
            Page<Books> booksPage = booksRepository.getPageable(sSearch, pageable);
            List<BooksWrapper> wrapperList = toWrapperList(booksPage.getContent());
            return new PageImpl<>(wrapperList, pageable, booksPage.getTotalElements());
        }
    }
}
