package com.dw2.reserva.controller;

import com.dw2.reserva.model.Login;
import com.dw2.reserva.service.LoginService;
import com.dw2.reserva.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RequestMapping
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ModelAndView list() {
        final ModelAndView modelAndView = new ModelAndView("Login");
        return modelAndView;
    }

    @PostMapping("/login")
    public String login(String email, String password, HttpSession session) {
        final Login login = new Login.Builder()
                .setEmail(email)
                .setPassword(password)
                .build();
        LOGGER.info("Login received for authentication: {}", login);
        try {
            loginService.isAuthenticated(login);
            session.setAttribute("login", login);
            return "redirect:/";
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("login");
        session.invalidate();
        return "redirect:login";
    }
}
