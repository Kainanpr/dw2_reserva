package com.dw2.reserva.controller;

import com.dw2.reserva.model.Equipment;
import com.dw2.reserva.model.ReserveEquipment;
import com.dw2.reserva.model.User;
import com.dw2.reserva.service.EquipmentService;
import com.dw2.reserva.service.ReserveEquipmentService;
import com.dw2.reserva.service.UserService;
import com.dw2.reserva.service.exception.ThereIsReserveForEquipmentException;
import com.dw2.reserva.service.exception.ThereIsReserveLaboratoryException;
import com.dw2.reserva.service.exception.UnableToRenewException;
import com.dw2.reserva.service.exception.UnableToReserveException;
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
@RequestMapping("/reserve-equipments")
public class ReserveEquipmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReserveEquipmentController.class);

    private final ReserveEquipmentService reserveEquipmentService;
    private final UserService userService;
    private final EquipmentService equipmentService;

    public ReserveEquipmentController(ReserveEquipmentService reserveEquipmentService, UserService userService, EquipmentService equipmentService) {
        this.reserveEquipmentService = reserveEquipmentService;
        this.userService = userService;
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public ModelAndView list() {
        final ModelAndView modelAndView = new ModelAndView("ListReserveEquipments");
        modelAndView.addObject("reserveEquipments", reserveEquipmentService.getAll());
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("equipments", equipmentService.getAll());
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                       Integer userId, Integer equipmentId) {
        final User user = new User.Builder()
                .setId(userId)
                .build();
        final Equipment equipment = new Equipment.Builder()
                .setId(equipmentId)
                .build();
        final ReserveEquipment reserveEquipment = new ReserveEquipment.Builder()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setUser(user)
                .setEquipment(equipment)
                .build();
        LOGGER.info("ReserveEquipment received to save: {}", reserveEquipment);

        try {
            reserveEquipmentService.save(reserveEquipment);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (ThereIsReserveForEquipmentException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body("Já existe uma reserva neste horário para o equipamento!");
        } catch (ThereIsReserveLaboratoryException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body("Já existe uma reserva neste horário para o laborátorio que o equipamento pertence!");
        } catch (UnableToReserveException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body("Reserva permitida com apenas 24 horas de antecedência!");
        }
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to delete: {}", id);
        reserveEquipmentService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id,
                                         @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                         @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                         Integer userId, Integer equipmentId) {
        final User user = new User.Builder()
                .setId(userId)
                .build();
        final Equipment equipment = new Equipment.Builder()
                .setId(equipmentId)
                .build();
        final ReserveEquipment reserveEquipment = new ReserveEquipment.Builder()
                .setId(id)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setUser(user)
                .setEquipment(equipment)
                .build();
        LOGGER.info("ID received to update: {}", id);
        LOGGER.info("ReserveEquipment received to update: {}", reserveEquipment);

        try {
            reserveEquipmentService.update(reserveEquipment);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (ThereIsReserveForEquipmentException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body("Já existe uma reserva neste horário para o equipamento!");
        } catch (ThereIsReserveLaboratoryException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body("Já existe uma reserva neste horário para o laborátorio que o equipamento pertence!");
        } catch (UnableToRenewException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body("Só pode ser renovado faltando 10 minutos para o termino da reserva!");
        } catch (UnableToReserveException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body("Reserva permitida com apenas 24 horas de antecedência!");
        }
    }
}
