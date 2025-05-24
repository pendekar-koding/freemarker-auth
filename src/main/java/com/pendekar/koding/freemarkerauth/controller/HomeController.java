package com.pendekar.koding.freemarkerauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping(value = {"/","/index", "/home"})
    public String getIndex(Map<String,Object> mapParam, Authentication authentication)throws Exception{
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(authentication.getPrincipal()));
        mapParam.put("auth", authentication);
        mapParam.put("pageTitle", "Index");
        return "index";
    }
}