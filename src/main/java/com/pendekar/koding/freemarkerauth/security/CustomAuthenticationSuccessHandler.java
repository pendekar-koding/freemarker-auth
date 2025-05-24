package com.pendekar.koding.freemarkerauth.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    public static final Logger logger = LogManager.getLogger(CustomAuthenticationSuccessHandler.class.getName());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info(authentication.getDetails());
        String redirectPage = "/index";

        try {
            setSession(httpServletRequest);
        }catch (Exception e){
            logger.error(e);
        }

        httpServletResponse.sendRedirect(redirectPage);
    }

    private void setSession(HttpServletRequest request)throws Exception{
        logger.info("Setting up few things for FrontEnd Attribute");
        HttpSession session = request.getSession();
        session.setAttribute("authentication", SecurityContextHolder.getContext().getAuthentication());
    }
}
