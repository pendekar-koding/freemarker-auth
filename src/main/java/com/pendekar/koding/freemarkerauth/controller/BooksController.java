package com.pendekar.koding.freemarkerauth.controller;

import com.pendekar.koding.freemarkerauth.common.controller.BaseController;
import com.pendekar.koding.freemarkerauth.service.master.BookService;
import com.pendekar.koding.freemarkerauth.wrapper.BooksWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping(value = "/master/book")
public class BooksController extends BaseController {

    private final BookService bookService;
    private final MessageSource messageSource;

    public BooksController(BookService bookService, MessageSource messageSource) {
        this.bookService = bookService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/list")
    public String getList(Map<String, Object> mapParam, Locale locale) throws Exception {
        mapParam.put("pageTitle",messageSource.getMessage("page.book.title",new Object[]{}, locale));
        return "store/book/list";
    }

    @RequestMapping(value = "/new")
    public String addNew(Map<String, Object> mapParam,Locale locale) throws Exception {
        mapParam.put("pageTitle",messageSource.getMessage("page.book.title",new Object[]{}, locale));
        mapParam.put("model", new BooksWrapper());
        return "store/book/new";
    }

    @RequestMapping(value = "/edit")
    public String edit(Map<String, Object> mapParam, @RequestParam Long id, HttpServletRequest request, Locale locale) throws Exception {
        mapParam.put("pageTitle",messageSource.getMessage("page.book.title",new Object[]{}, locale));
        mapParam.put("model", bookService.getById(id));
        return "store/book/edit";
    }

    @RequestMapping(value = "getDataList", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getDataList(HttpServletRequest request,
                                                         @RequestParam(required = true) String sEcho, @RequestParam(required = true) String sColumns,
                                                         @RequestParam(required = true) String sSearch, @RequestParam(required = true) Integer iDisplayStart,
                                                         @RequestParam(required = true) Integer iDisplayLength, @RequestParam(required = true) Integer iSortCol_0,
                                                         @RequestParam(required = true) String sSortDir_0) {
        Map<String, Object> rMap = new HashMap<>();
        Sort sort = Sort.by(Sort.Direction.fromString(sSortDir_0.toUpperCase()),
                sColumns.split(",")[iSortCol_0]);
        try {
            Page<BooksWrapper> wrappers = bookService.getPageableList(sSearch, iDisplayStart, iDisplayLength, sort);
            rMap.put("sEcho", sEcho);
            rMap.put("aaData", wrappers.getContent());
            rMap.put("iTotalRecords", (int) wrappers.getTotalElements());
            rMap.put("iTotalDisplayRecords", wrappers.getTotalElements());
            rMap.put("sColumns", sColumns);
        } catch (Exception e) {
            rMap.put("result", STR_ERROR);
            rMap.put("detail", e.getMessage());
        }

        return rMap;
    }
}
