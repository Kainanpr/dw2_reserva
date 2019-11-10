package com.dw2.reserva.controller;

import com.dw2.reserva.model.Laboratory;
import com.dw2.reserva.model.TypeEnum;
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
@RequestMapping("/laboratories")
public class LaboratoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LaboratoryController.class);

    private final LaboratoryService laboratoryService;

    public LaboratoryController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    @GetMapping
    public ModelAndView list() {
        final ModelAndView modelAndView = new ModelAndView("ListLaboratories");
        modelAndView.addObject("laboratories", laboratoryService.getAll());
        return modelAndView;
    }

    @PostMapping
    public String save(String name) {
        final Laboratory laboratory = new Laboratory.Builder()
                .setName(name)
                .build();
        LOGGER.info("Laboratory received to save: {}", laboratory);
        laboratoryService.save(laboratory);
        return "redirect:/laboratories";
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to delete: {}", id);
        laboratoryService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id, String name) {
        final Laboratory laboratory = new Laboratory.Builder()
                .setId(id)
                .setName(name)
                .build();
        LOGGER.info("ID received to update: {}", id);
        LOGGER.info("Laboratory received to update: {}", laboratory);
        laboratoryService.update(laboratory);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
