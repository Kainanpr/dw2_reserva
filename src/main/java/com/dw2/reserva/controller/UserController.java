package com.dw2.reserva.controller;

import com.dw2.reserva.controller.dto.read.UserReadDto;
import com.dw2.reserva.controller.dto.write.UserWriteDto;
import com.dw2.reserva.model.TypeEnum;
import com.dw2.reserva.model.User;
import com.dw2.reserva.service.UserService;
import com.dw2.reserva.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView list() {
        final ModelAndView modelAndView = new ModelAndView("ListUsers");
        modelAndView.addObject("users", userService.getAll());
        return modelAndView;
    }

    @PostMapping
    public String save(String name, String email, String cpf, TypeEnum type) {
        final User user = new User.Builder()
                .setName(name)
                .setEmail(email)
                .setCpf(cpf)
                .setType(type)
                .build();
        LOGGER.info("User received to save: {}", user);
        userService.save(user);
        return "redirect:/users";
    }
}
