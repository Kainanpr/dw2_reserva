package com.dw2.reserva.service;

import com.dw2.reserva.model.Equipment;
import com.dw2.reserva.model.ReserveEquipment;
import com.dw2.reserva.model.ReserveLaboratory;
import com.dw2.reserva.persistence.repository.EquipmentRepository;
import com.dw2.reserva.persistence.repository.ReserveEquipmentRepository;
import com.dw2.reserva.persistence.repository.ReserveLaboratoryRepository;
import com.dw2.reserva.service.exception.ExistingReservationException;
import com.dw2.reserva.service.exception.ObjectNotFoundException;
import com.dw2.reserva.service.exception.UnableToReserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReserveEquipmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReserveEquipmentService.class);
    private final Clock clock;

    private final ReserveEquipmentRepository reserveEquipmentRepository;
    private final ReserveLaboratoryRepository reserveLaboratoryRepository;
    private final EquipmentRepository equipmentRepository;

    public ReserveEquipmentService(ReserveEquipmentRepository reserveEquipmentRepository, ReserveLaboratoryRepository reserveLaboratoryRepository, EquipmentRepository equipmentRepository, Clock clock) {
        this.reserveEquipmentRepository = reserveEquipmentRepository;
        this.reserveLaboratoryRepository = reserveLaboratoryRepository;
        this.equipmentRepository = equipmentRepository;
        this.clock = clock;
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
        checkForReservation(reserveEquipment);
        final int savedId = reserveEquipmentRepository.save(reserveEquipment);

        final ReserveEquipment savedReserveEquipment = reserveEquipmentRepository.getById(savedId);
        LOGGER.info("Saved ReserveEquipment: {}", savedReserveEquipment);
        return savedReserveEquipment;
    }

    private void checkForReservation(ReserveEquipment reserveEquipment) {
        final LocalDateTime now = LocalDateTime.now(clock);

        // Correcting daylight(DST) saving time in 2019
        if (reserveEquipment.getStartDate().minusHours(23).isBefore(now)) {
            throw new UnableToReserveException("Reservation allowed only 24 hours in advance");
        }

        final Equipment requestedEquipment = equipmentRepository.getById(reserveEquipment.getEquipment().getId());
        final List<ReserveLaboratory> reserveLaboratoryList = reserveLaboratoryRepository.getAll();
        final List<ReserveEquipment> reserveEquipmentList = reserveEquipmentRepository.getAll();
        boolean isReserved = false;

        for (ReserveLaboratory reserve : reserveLaboratoryList) {
            if ((reserve.getLaboratory().getId().equals(requestedEquipment.getLaboratory().getId()))
                    && ((reserveEquipment.getStartDate().isAfter(reserve.getStartDate()) || reserveEquipment.getStartDate().isEqual(reserve.getStartDate()))
                    && (reserveEquipment.getStartDate().isBefore(reserve.getEndDate()) || reserveEquipment.getStartDate().isEqual(reserve.getEndDate())))
                    || ((reserveEquipment.getEndDate().isBefore(reserve.getEndDate()) || reserveEquipment.getEndDate().isEqual(reserve.getEndDate()))
                    && (reserveEquipment.getEndDate().isAfter(reserve.getStartDate()) || reserveEquipment.getEndDate().isEqual(reserve.getStartDate())))) {
                isReserved = true;
                break;
            }
        }

        for (ReserveEquipment reserve : reserveEquipmentList) {
            if ((reserve.getEquipment().getId().equals(requestedEquipment.getId()))
                    && ((reserveEquipment.getStartDate().isAfter(reserve.getStartDate()) || reserveEquipment.getStartDate().isEqual(reserve.getStartDate()))
                    && (reserveEquipment.getStartDate().isBefore(reserve.getEndDate()) || reserveEquipment.getStartDate().isEqual(reserve.getEndDate())))
                    || ((reserveEquipment.getEndDate().isBefore(reserve.getEndDate()) || reserveEquipment.getEndDate().isEqual(reserve.getEndDate()))
                    && (reserveEquipment.getEndDate().isAfter(reserve.getStartDate()) || reserveEquipment.getEndDate().isEqual(reserve.getStartDate())))) {
                isReserved = true;
                break;
            }
        }

        if (isReserved) {
            throw new ExistingReservationException("There is already a reservation");
        }
    }

    @Transactional
    public ReserveEquipment update(ReserveEquipment reserveEquipment) {
        checkForReservation(reserveEquipment);
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
