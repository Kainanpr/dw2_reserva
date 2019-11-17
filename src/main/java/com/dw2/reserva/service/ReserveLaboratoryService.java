package com.dw2.reserva.service;

import com.dw2.reserva.model.ReserveLaboratory;
import com.dw2.reserva.persistence.repository.ReserveLaboratoryRepository;
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
public class ReserveLaboratoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReserveLaboratoryService.class);
    private final Clock clock;

    private final ReserveLaboratoryRepository reserveLaboratoryRepository;

    public ReserveLaboratoryService(ReserveLaboratoryRepository reserveLaboratoryRepository, Clock clock) {
        this.reserveLaboratoryRepository = reserveLaboratoryRepository;
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
