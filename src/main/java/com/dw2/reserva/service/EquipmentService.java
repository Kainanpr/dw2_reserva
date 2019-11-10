package com.dw2.reserva.service;

import com.dw2.reserva.model.Equipment;
import com.dw2.reserva.persistence.repository.EquipmentRepository;
import com.dw2.reserva.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentService.class);

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment getById(Integer id) {
        final Equipment equipment = equipmentRepository.getById(id);

        if (equipment == null) {
            throw new ObjectNotFoundException("Equipment not found! ID: " + id);
        }

        LOGGER.info("Retrieved equipment by ID: {}", equipment);
        return equipment;
    }

    public List<Equipment> getAll() {
        final List<Equipment> equipmentList = equipmentRepository.getAll();
        LOGGER.info("Retrieved all equipments: {}", equipmentList);
        return equipmentList;
    }

    @Transactional
    public Equipment save(Equipment equipment) {
        final int savedId = equipmentRepository.save(equipment);

        final Equipment savedEquipment = equipmentRepository.getById(savedId);
        LOGGER.info("Saved Equipment: {}", savedEquipment);
        return savedEquipment;
    }

    @Transactional
    public Equipment update(Equipment equipment) {
        final int equipmentId = equipment.getId();
        final int affectedRows = equipmentRepository.update(equipment);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find equipment with id (not updated): " + equipmentId);
        }

        final Equipment updatedEquipment = equipmentRepository.getById(equipmentId);
        LOGGER.info("Updated Equipment: {}", updatedEquipment);
        return updatedEquipment;
    }

    @Transactional
    public void delete(Integer id) {
        final int affectedRows = equipmentRepository.delete(id);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find equipment with id (not deleted): " + id);
        }

        LOGGER.info("Deleted equipment (deleted rows: {})", affectedRows);
    }
}
