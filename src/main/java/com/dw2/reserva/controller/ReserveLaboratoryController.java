package com.dw2.reserva.controller;

import com.dw2.reserva.model.Laboratory;
import com.dw2.reserva.model.ReserveLaboratory;
import com.dw2.reserva.model.User;
import com.dw2.reserva.service.LaboratoryService;
import com.dw2.reserva.service.ReserveLaboratoryService;
import com.dw2.reserva.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@CrossOrigin
@Controller
@RequestMapping("/reserve-laboratories")
public class ReserveLaboratoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReserveLaboratoryController.class);

    private final ReserveLaboratoryService reserveLaboratoryService;
    private final UserService userService;
    private final LaboratoryService laboratoryService;

    public ReserveLaboratoryController(ReserveLaboratoryService reserveLaboratoryService, UserService userService, LaboratoryService laboratoryService) {
        this.reserveLaboratoryService = reserveLaboratoryService;
        this.userService = userService;
        this.laboratoryService = laboratoryService;
    }

    @GetMapping
    public ModelAndView list() {
        final ModelAndView modelAndView = new ModelAndView("ListReserveLaboratories");
        modelAndView.addObject("reserveLaboratories", reserveLaboratoryService.getAll());
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("laboratories", laboratoryService.getAll());
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                       Integer userId, Integer laboratoryId) {
        final User user = new User.Builder()
                .setId(userId)
                .build();
        final Laboratory laboratory = new Laboratory.Builder()
                .setId(laboratoryId)
                .build();
        final ReserveLaboratory reserveLaboratory = new ReserveLaboratory.Builder()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setUser(user)
                .setLaboratory(laboratory)
                .build();
        LOGGER.info("ReserveLaboratory received to save: {}", reserveLaboratory);
        reserveLaboratoryService.save(reserveLaboratory);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to delete: {}", id);
        reserveLaboratoryService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id,
                                         @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                         @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                         Integer userId, Integer laboratoryId) {
        final User user = new User.Builder()
                .setId(userId)
                .build();
        final Laboratory laboratory = new Laboratory.Builder()
                .setId(laboratoryId)
                .build();
        final ReserveLaboratory reserveLaboratory = new ReserveLaboratory.Builder()
                .setId(id)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setUser(user)
                .setLaboratory(laboratory)
                .build();
        LOGGER.info("ID received to update: {}", id);
        LOGGER.info("ReserveLaboratory received to update: {}", reserveLaboratory);
        reserveLaboratoryService.update(reserveLaboratory);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
