package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.ReserveLaboratory;

import java.util.List;

public interface ReserveLaboratoryRepository {
    ReserveLaboratory getById(Integer id);

    List<ReserveLaboratory> getAll();

    int save(ReserveLaboratory reserveLaboratory);

    int update(ReserveLaboratory reserveLaboratory);

    int delete(Integer id);
}
