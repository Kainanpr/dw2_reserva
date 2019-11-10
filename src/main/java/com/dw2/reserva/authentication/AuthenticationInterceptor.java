package com.dw2.reserva.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
        final String uri = request.getRequestURI();
        LOGGER.info("URI received: {}", uri);

        if (uri.endsWith("login") && request.getSession().getAttribute("login") != null) {
            response.sendRedirect("/");
            return true;
        }

        if (uri.endsWith("login")) {
            return true;
        }

        if (request.getSession().getAttribute("login") != null) {
            return true;
        }

        response.sendRedirect("login");
        return false;
    }
}
