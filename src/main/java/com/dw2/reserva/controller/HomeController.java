package com.dw2.reserva.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@Controller
@RequestMapping("/")
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public ModelAndView home() {
        final ModelAndView modelAndView = new ModelAndView("Home");
        return modelAndView;
    }
}
