package com.dw2.reserva.service;

import com.dw2.reserva.model.Laboratory;
import com.dw2.reserva.persistence.repository.LaboratoryRepository;
import com.dw2.reserva.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LaboratoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LaboratoryService.class);

    private final LaboratoryRepository laboratoryRepository;

    public LaboratoryService(LaboratoryRepository laboratoryRepository) {
        this.laboratoryRepository = laboratoryRepository;
    }

    public Laboratory getById(Integer id) {
        final Laboratory laboratory = laboratoryRepository.getById(id);

        if (laboratory == null) {
            throw new ObjectNotFoundException("Laboratory not found! ID: " + id);
        }

        LOGGER.info("Retrieved laboratory by ID: {}", laboratory);
        return laboratory;
    }

    public List<Laboratory> getAll() {
        final List<Laboratory> laboratoryList = laboratoryRepository.getAll();
        LOGGER.info("Retrieved all laboratories: {}", laboratoryList);
        return laboratoryList;
    }

    @Transactional
    public Laboratory save(Laboratory laboratory) {
        final int savedId = laboratoryRepository.save(laboratory);

        final Laboratory savedLaboratory = laboratoryRepository.getById(savedId);
        LOGGER.info("Saved Laboratory: {}", savedLaboratory);
        return savedLaboratory;
    }

    @Transactional
    public Laboratory update(Laboratory laboratory) {
        final int laboratoryId = laboratory.getId();
        final int affectedRows = laboratoryRepository.update(laboratory);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find laboratory with id (not updated): " + laboratoryId);
        }

        final Laboratory updatedLaboratory = laboratoryRepository.getById(laboratoryId);
        LOGGER.info("Updated Laboratory: {}", updatedLaboratory);
        return updatedLaboratory;
    }

    @Transactional
    public void delete(Integer id) {
        final int affectedRows = laboratoryRepository.delete(id);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find laboratory with id (not deleted): " + id);
        }

        LOGGER.info("Deleted laboratory (deleted rows: {})", affectedRows);
    }
}
