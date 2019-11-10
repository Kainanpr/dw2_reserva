package com.dw2.reserva.controller;

import com.dw2.reserva.model.Equipment;
import com.dw2.reserva.model.Laboratory;
import com.dw2.reserva.service.EquipmentService;
import com.dw2.reserva.service.LaboratoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@Controller
@RequestMapping("/equipments")
public class EquipmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentController.class);

    private final EquipmentService equipmentService;
    private final LaboratoryService laboratoryService;

    public EquipmentController(EquipmentService equipmentService, LaboratoryService laboratoryService) {
        this.equipmentService = equipmentService;
        this.laboratoryService = laboratoryService;
    }

    @GetMapping
    public ModelAndView list() {
        final ModelAndView modelAndView = new ModelAndView("ListEquipments");
        modelAndView.addObject("equipments", equipmentService.getAll());
        modelAndView.addObject("laboratories", laboratoryService.getAll());
        return modelAndView;
    }

    @PostMapping
    public String save(String name, Integer laboratoryId) {
        final Laboratory laboratory = new Laboratory.Builder()
                .setId(laboratoryId)
                .build();
        final Equipment equipment = new Equipment.Builder()
                .setName(name)
                .setLaboratory(laboratory)
                .build();
        LOGGER.info("Equipment received to save: {}", equipment);
        equipmentService.save(equipment);
        return "redirect:/equipments";
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to delete: {}", id);
        equipmentService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id, String name, Integer laboratoryId) {
        final Laboratory laboratory = new Laboratory.Builder()
                .setId(laboratoryId)
                .build();
        final Equipment equipment = new Equipment.Builder()
                .setId(id)
                .setName(name)
                .setLaboratory(laboratory)
                .build();
        LOGGER.info("ID received to update: {}", id);
        LOGGER.info("Equipment received to update: {}", equipment);
        equipmentService.update(equipment);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
