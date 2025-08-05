package com.pendekar.koding.freemarkerauth.controller;

import com.pendekar.koding.freemarkerauth.config.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("auth")
    public CustomUserDetails globalAuth(Authentication authentication) {
        return authentication != null ? (CustomUserDetails) authentication.getPrincipal() : null;
    }
}
