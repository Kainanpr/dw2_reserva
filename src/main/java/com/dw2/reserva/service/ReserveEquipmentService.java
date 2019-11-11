package com.dw2.reserva.service;

import com.dw2.reserva.model.ReserveEquipment;
import com.dw2.reserva.persistence.repository.ReserveEquipmentRepository;
import com.dw2.reserva.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReserveEquipmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReserveEquipmentService.class);

    private final ReserveEquipmentRepository reserveEquipmentRepository;

    public ReserveEquipmentService(ReserveEquipmentRepository reserveEquipmentRepository) {
        this.reserveEquipmentRepository = reserveEquipmentRepository;
    }

    public ReserveEquipment getById(Integer id) {
        final ReserveEquipment reserveEquipment = reserveEquipmentRepository.getById(id);

        if (reserveEquipment == null) {
            throw new ObjectNotFoundException("ReserveEquipment not found! ID: " + id);
        }

        LOGGER.info("Retrieved reserveEquipment by ID: {}", reserveEquipment);
        return reserveEquipment;
    }

    public List<ReserveEquipment> getAll() {
        final List<ReserveEquipment> reserveEquipmentList = reserveEquipmentRepository.getAll();
        LOGGER.info("Retrieved all reserveEquipments: {}", reserveEquipmentList);
        return reserveEquipmentList;
    }

    @Transactional
    public ReserveEquipment save(ReserveEquipment reserveEquipment) {
        final int savedId = reserveEquipmentRepository.save(reserveEquipment);

        final ReserveEquipment savedReserveEquipment = reserveEquipmentRepository.getById(savedId);
        LOGGER.info("Saved ReserveEquipment: {}", savedReserveEquipment);
        return savedReserveEquipment;
    }

    @Transactional
    public ReserveEquipment update(ReserveEquipment reserveEquipment) {
        final int reserveEquipmentId = reserveEquipment.getId();
        final int affectedRows = reserveEquipmentRepository.update(reserveEquipment);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find reserveEquipment with id (not updated): " + reserveEquipmentId);
        }

        final ReserveEquipment updatedReserveEquipment = reserveEquipmentRepository.getById(reserveEquipmentId);
        LOGGER.info("Updated ReserveEquipment: {}", updatedReserveEquipment);
        return updatedReserveEquipment;
    }

    @Transactional
    public void delete(Integer id) {
        final int affectedRows = reserveEquipmentRepository.delete(id);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find reserveEquipment with id (not deleted): " + id);
        }

        LOGGER.info("Deleted reserveEquipment (deleted rows: {})", affectedRows);
    }
}
