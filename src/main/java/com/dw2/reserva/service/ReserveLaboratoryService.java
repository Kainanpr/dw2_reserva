package com.dw2.reserva.service;

import com.dw2.reserva.model.Laboratory;
import com.dw2.reserva.model.ReserveEquipment;
import com.dw2.reserva.model.ReserveLaboratory;
import com.dw2.reserva.persistence.repository.LaboratoryRepository;
import com.dw2.reserva.persistence.repository.ReserveEquipmentRepository;
import com.dw2.reserva.persistence.repository.ReserveLaboratoryRepository;
import com.dw2.reserva.service.exception.ObjectNotFoundException;
import com.dw2.reserva.service.exception.ThereIsReserveForEquipmentException;
import com.dw2.reserva.service.exception.ThereIsReserveLaboratoryException;
import com.dw2.reserva.service.exception.UnableToReserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReserveLaboratoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReserveLaboratoryService.class);
    private final Clock clock;

    private final ReserveLaboratoryRepository reserveLaboratoryRepository;
    private final ReserveEquipmentRepository reserveEquipmentRepository;
    private final LaboratoryRepository laboratoryRepository;

    public ReserveLaboratoryService(ReserveLaboratoryRepository reserveLaboratoryRepository, ReserveEquipmentRepository reserveEquipmentRepository, LaboratoryRepository laboratoryRepository, Clock clock) {
        this.reserveLaboratoryRepository = reserveLaboratoryRepository;
        this.reserveEquipmentRepository = reserveEquipmentRepository;
        this.laboratoryRepository = laboratoryRepository;
        this.clock = clock;
    }

    public ReserveLaboratory getById(Integer id) {
        final ReserveLaboratory reserveLaboratory = reserveLaboratoryRepository.getById(id);

        if (reserveLaboratory == null) {
            throw new ObjectNotFoundException("ReserveLaboratory not found! ID: " + id);
        }

        LOGGER.info("Retrieved reserveLaboratory by ID: {}", reserveLaboratory);
        return reserveLaboratory;
    }

    public List<ReserveLaboratory> getAll() {
        final List<ReserveLaboratory> reserveLaboratoryList = reserveLaboratoryRepository.getAll();
        LOGGER.info("Retrieved all reserveLaboratories: {}", reserveLaboratoryList);
        return reserveLaboratoryList;
    }

    @Transactional
    public ReserveLaboratory save(ReserveLaboratory reserveLaboratory) {
        checkForReservation(reserveLaboratory);
        final int savedId = reserveLaboratoryRepository.save(reserveLaboratory);

        final ReserveLaboratory savedReserveLaboratory = reserveLaboratoryRepository.getById(savedId);
        LOGGER.info("Saved ReserveLaboratory: {}", savedReserveLaboratory);
        return savedReserveLaboratory;
    }

    private void checkForReservation(ReserveLaboratory reserveLaboratory) {
        final LocalDateTime now = LocalDateTime.now(clock);

        // Correcting daylight(DST) saving time in 2019
        if (reserveLaboratory.getStartDate().minusHours(47).isBefore(now)) {
            throw new UnableToReserveException("Reservation allowed only 48 hours in advance");
        }

        final Laboratory requestedLaboratory = laboratoryRepository.getById(reserveLaboratory.getLaboratory().getId());
        final List<ReserveLaboratory> reserveLaboratoryList = reserveLaboratoryRepository.getAll();
        final List<ReserveEquipment> reserveEquipmentList = reserveEquipmentRepository.getAll();

        for (ReserveLaboratory reserve : reserveLaboratoryList) {
            if ((reserve.getLaboratory().getId().equals(requestedLaboratory.getId()) && !reserve.getId().equals(reserveLaboratory.getId()))
                    && (((reserveLaboratory.getStartDate().isAfter(reserve.getStartDate()) || reserveLaboratory.getStartDate().isEqual(reserve.getStartDate()))
                    && (reserveLaboratory.getStartDate().isBefore(reserve.getEndDate()) || reserveLaboratory.getStartDate().isEqual(reserve.getEndDate())))
                    || ((reserveLaboratory.getEndDate().isBefore(reserve.getEndDate()) || reserveLaboratory.getEndDate().isEqual(reserve.getEndDate()))
                    && (reserveLaboratory.getEndDate().isAfter(reserve.getStartDate()) || reserveLaboratory.getEndDate().isEqual(reserve.getStartDate()))))) {
                throw new ThereIsReserveLaboratoryException("There is already a reservation for the laboratory");
            }
        }

        for (ReserveEquipment reserve : reserveEquipmentList) {
            if ((reserve.getEquipment().getLaboratory().getId().equals(requestedLaboratory.getId()))
                    && (((reserveLaboratory.getStartDate().isAfter(reserve.getStartDate()) || reserveLaboratory.getStartDate().isEqual(reserve.getStartDate()))
                    && (reserveLaboratory.getStartDate().isBefore(reserve.getEndDate()) || reserveLaboratory.getStartDate().isEqual(reserve.getEndDate())))
                    || ((reserveLaboratory.getEndDate().isBefore(reserve.getEndDate()) || reserveLaboratory.getEndDate().isEqual(reserve.getEndDate()))
                    && (reserveLaboratory.getEndDate().isAfter(reserve.getStartDate()) || reserveLaboratory.getEndDate().isEqual(reserve.getStartDate()))))) {
                throw new ThereIsReserveForEquipmentException("There is already a reservation for the equipment");
            }
        }
    }

    @Transactional
    public ReserveLaboratory update(ReserveLaboratory reserveLaboratory) {
        checkForReservation(reserveLaboratory);
        final int reserveLaboratoryId = reserveLaboratory.getId();
        final int affectedRows = reserveLaboratoryRepository.update(reserveLaboratory);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find reserveLaboratory with id (not updated): " + reserveLaboratoryId);
        }

        final ReserveLaboratory updatedReserveLaboratory = reserveLaboratoryRepository.getById(reserveLaboratoryId);
        LOGGER.info("Updated ReserveLaboratory: {}", updatedReserveLaboratory);
        return updatedReserveLaboratory;
    }

    @Transactional
    public void delete(Integer id) {
        final int affectedRows = reserveLaboratoryRepository.delete(id);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find reserveLaboratory with id (not deleted): " + id);
        }

        LOGGER.info("Deleted reserveLaboratory (deleted rows: {})", affectedRows);
    }
}
