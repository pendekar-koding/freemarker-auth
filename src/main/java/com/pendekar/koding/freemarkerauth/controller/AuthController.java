package com.pendekar.koding.freemarkerauth.controller;

import com.pendekar.koding.freemarkerauth.common.controller.BaseController;
import com.pendekar.koding.freemarkerauth.service.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;
import java.util.Map;

@Controller
public class AuthController extends BaseController {

    private final UserProfileService userProfileService;
    private final MessageSource messageSource;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserProfileService userProfileService, MessageSource messageSource, MessageSource messageSource1, AuthenticationManager authenticationManager) {
        this.userProfileService = userProfileService;
        this.messageSource = messageSource1;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Map<String, Object> mapParam, Locale locale) {
        mapParam.put("pageTitle", messageSource.getMessage("application.name", new Object[]{}, locale));
        return "login";
    }
}
